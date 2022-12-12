package ru.vsu.csf.asashina.universitysystem.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.vsu.csf.asashina.universitysystem.filter.AuthorizationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration implements WebMvcConfigurer {

    public static final String LECTURER = "LECTURER";
    public static final String PARTICIPANT = "PARTICIPANT";
    public static final String DEAN = "DEAN";
    public static final String ADMIN = "ADMIN";

    private final AuthorizationFilter authorizationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeHttpRequests()
                .requestMatchers(PUT, "/course/*").hasAnyAuthority(LECTURER)

                .requestMatchers(GET, "/project", "/project/*").hasAnyAuthority(PARTICIPANT)

                .requestMatchers(POST, "/institute", "/project", "/project/**", "/course", "/course/**")
                    .hasAnyAuthority(DEAN)
                .requestMatchers(PUT, "/institute/*", "/course/*").hasAnyAuthority(DEAN)
                .requestMatchers(DELETE, "/institute/*", "/project/**", "/course/**").hasAnyAuthority(DEAN)

                .requestMatchers(POST, "/faculty", "/employee/*").hasAnyAuthority(ADMIN)
                .requestMatchers(PUT, "/faculty").hasAnyAuthority(ADMIN)
                .requestMatchers(DELETE, "/faculty/*", "/employee/**").hasAnyAuthority(ADMIN)

                .anyRequest().permitAll();

        http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*");
    }
}
