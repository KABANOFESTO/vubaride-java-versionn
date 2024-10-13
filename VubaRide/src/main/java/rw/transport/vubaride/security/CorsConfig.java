package rw.transport.vubaride.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {
    private static final long MAX_AGE = 3600L;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Allow credentials (session cookies, etc.)
        config.setAllowCredentials(true);
        
        // Allow multiple origins
        config.setAllowedOrigins(List.of(
            "http://localhost:5173",  // Frontend server (React, Vue, etc.)
            "http://127.0.0.1:5501"   // Local development server
        ));
        
        // Allow all headers that might be used in requests
        config.setAllowedHeaders(List.of(
            HttpHeaders.AUTHORIZATION, // JWT Token in Authorization header
            HttpHeaders.CONTENT_TYPE,  // Content-Type for POST/PUT requests
            HttpHeaders.ACCEPT,        // Accept header for responses
            HttpHeaders.ORIGIN,        // Origin header for requests
            HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, // Preflight method type
            HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS, // Preflight request headers
            "X-Requested-With"         // Used in AJAX requests
        ));
        
        // Allow common HTTP methods
        config.setAllowedMethods(List.of(
            HttpMethod.GET.name(),
            HttpMethod.POST.name(),
            HttpMethod.PUT.name(),
            HttpMethod.DELETE.name(),
            HttpMethod.OPTIONS.name(),
            HttpMethod.PATCH.name()
        ));
        
        // Expose specific headers to the client (for custom headers or tokens)
        config.setExposedHeaders(List.of(
            HttpHeaders.AUTHORIZATION,  // Expose JWT token
            HttpHeaders.CONTENT_DISPOSITION // For file downloads
        ));
        
        // Cache the preflight response for 1 hour
        config.setMaxAge(MAX_AGE);
        
        // Apply the configuration to all routes
        source.registerCorsConfiguration("/**", config);
        
        return new CorsFilter(source);
    }
}
