package com.LT.store.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LocalhostOnlyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String remoteAddr = request.getRemoteAddr();
        if ("127.0.0.1".equals(remoteAddr) || "0:0:0:0:0:0:0:1".equals(remoteAddr)) {
            chain.doFilter(request, response); // allow localhost
        } else {
            ((jakarta.servlet.http.HttpServletResponse) response)
                    .sendError(403, "Forbidden");
        }
    }
}

