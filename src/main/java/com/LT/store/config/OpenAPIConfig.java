//package com.LT.store.config;
//
//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
//import io.swagger.v3.oas.annotations.info.Info;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//import io.swagger.v3.oas.annotations.security.SecurityScheme;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.security.OAuthFlows;
//import io.swagger.v3.oas.models.security.OAuthFlow;
//import io.swagger.v3.oas.models.security.Scopes;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@OpenAPIDefinition(
//        info = @Info(title = "Mom Store API", version = "v1"),
//        security = {
//            @SecurityRequirement(name = "bearerAuth"),
//            @SecurityRequirement(name = "oauth2")
//        }
//)
//@SecurityScheme(
//        name = "bearerAuth",
//        type = SecuritySchemeType.HTTP,
//        bearerFormat = "JWT",
//        scheme = "bearer"
//)
//public class OpenAPIConfig {
//    @Bean
//    public OpenAPI customOpenAPI() {
//        Components components = new Components()
//                .addSecuritySchemes("bearerAuth",
//                        new io.swagger.v3.oas.models.security.SecurityScheme()
//                                .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
//                                .scheme("bearer")
//                                .bearerFormat("JWT"))
//                .addSecuritySchemes("oauth2",
//                        new io.swagger.v3.oas.models.security.SecurityScheme()
//                                .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.OAUTH2)
//                                .flows(new OAuthFlows()
//                                        .authorizationCode(new OAuthFlow()
//                                                .authorizationUrl("https://accounts.google.com/o/oauth2/v2/auth")
//                                                .tokenUrl("https://oauth2.googleapis.com/token")
//                                                .scopes(new Scopes()
//                                                        .addString("openid", "OpenID scope")
//                                                        .addString("profile", "Profile info")
//                                                        .addString("email", "Email address")
//                                                )
//                                        )
//                                )
//                );
//        return new OpenAPI()
//                .components(components)
//                .info(new io.swagger.v3.oas.models.info.Info()
//                        .title("Mom Store API")
//                        .version("v1")
//                        .description("API documentation for Mom Store application"))
//                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("bearerAuth"))
//                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("oauth2"));
//    }
//}
