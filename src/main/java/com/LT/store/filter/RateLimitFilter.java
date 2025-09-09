package com.LT.store.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(RateLimitFilter.class);
    private final Map<String, RequestInfo> requestCounts = new ConcurrentHashMap<>();
    private final Map<String, BanInfo> bannedIps = new ConcurrentHashMap<>();
    private static final int MAX_REQUESTS = 10;   // limit per window
    private static final long WINDOW_MS = 60_000; // 1 minute
    private static final int BAN_THRESHOLD = 3; // Number of times to exceed before ban
    private static final long BAN_TIME_MS = 5 * 60_000; // 5 minutes
    private static final long BAN = BAN_TIME_MS/1000;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;
        String clientIp = "127.0.0.1"; // Default to localhost for testing
                                        // getClientIp(httpReq);    for production
        long now = Instant.now().toEpochMilli();

        // Clean up expired bans
        bannedIps.entrySet().removeIf(entry -> now > entry.getValue().banExpiresAt);

        // Check if IP is banned
        BanInfo banInfo = bannedIps.get(clientIp);
        if (banInfo != null && now < banInfo.banExpiresAt) {
            logger.warn("Blocked banned IP: {}", clientIp);
            httpRes.setStatus(429);
            httpRes.getWriter().write("Its either youre spamming or youre a bot net, you are temporarily banned. Try again after " + BAN + " seconds." );
            return;
        }

        // Clean up old request info
        requestCounts.entrySet().removeIf(entry -> now - entry.getValue().windowStart > 10 * WINDOW_MS);

        RequestInfo info = requestCounts.getOrDefault(clientIp, new RequestInfo(0, now, 0));
        if (now - info.windowStart >= WINDOW_MS) {
            info = new RequestInfo(0, now, info.exceedCount);
        }
        info.count++;
        if (info.count > MAX_REQUESTS) {
            info.exceedCount++;
            logger.warn("Rate limit exceeded for IP: {} (exceedCount={})", clientIp, info.exceedCount);
            if (info.exceedCount >= BAN_THRESHOLD) {
                bannedIps.put(clientIp, new BanInfo(now + BAN_TIME_MS));
                requestCounts.remove(clientIp);
                httpRes.setStatus(429);
                httpRes.getWriter().write("Too many requests. You are temporarily banned. Try again later.");
                return;
            }
            requestCounts.put(clientIp, info);
            httpRes.setStatus(429);
            httpRes.getWriter().write("Too many requests. Please slow down.");
            return;
        }
        requestCounts.put(clientIp, info);
        chain.doFilter(request, response);
    }

    private String getClientIp(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    private static class RequestInfo {
        int count;
        long windowStart;
        int exceedCount;
        RequestInfo(int count, long windowStart, int exceedCount) {
            this.count = count;
            this.windowStart = windowStart;
            this.exceedCount = exceedCount;
        }
    }
    private static class BanInfo {
        long banExpiresAt;
        BanInfo(long banExpiresAt) {
            this.banExpiresAt = banExpiresAt;
        }
    }
}
