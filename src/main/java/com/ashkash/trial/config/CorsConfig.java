
package com.ashkash.trial.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Allow requests from the specified origin (e.g., http://localhost:3000)
        config.addAllowedOrigin("http://localhost:3000");

        // You can customize other CORS settings as needed
        // For example, allowing specific HTTP methods, headers, etc.

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
