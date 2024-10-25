package com.devtcc.tccback.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // Configure CORS explicitamente
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/usuario").hasRole("ADMINISTRADOR")                
                .requestMatchers(HttpMethod.PUT, "/usuario").hasRole("ADMINISTRADOR")                
                .requestMatchers(HttpMethod.POST, "/usuario").hasRole("ADMINISTRADOR")                
                .requestMatchers(HttpMethod.GET, "/ativo").permitAll()
                .requestMatchers(HttpMethod.POST, "/ativo").permitAll()
                .requestMatchers(HttpMethod.PUT, "/ativo").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/ativo").permitAll()
                .requestMatchers(HttpMethod.GET, "/ativo/dashboard").permitAll()
                .requestMatchers(HttpMethod.GET, "/ativo/download").permitAll()
                //.requestMatchers(HttpMethod.POST, "/ativo/validar").hasAnyRole("VALIDADOR", "ADMINISTRADOR")
                .requestMatchers(HttpMethod.POST, "/ativo/validar").permitAll()
                //.requestMatchers(HttpMethod.GET, "/ativo/validar").hasAnyRole("VALIDADOR", "ADMINISTRADOR")     
                .requestMatchers(HttpMethod.GET, "/ativo/validar").permitAll()     
                .requestMatchers(HttpMethod.PUT, "/ativo/validar").permitAll() 
                .requestMatchers(HttpMethod.GET, "/ativo/usuario/{fk_usario_id}").permitAll()    
                .requestMatchers(HttpMethod.PUT, "/ativo/usuario/{fk_usario_id}").permitAll()    
                .requestMatchers(HttpMethod.GET, "/checklist").permitAll()   
                .requestMatchers(HttpMethod.POST, "/checklist").permitAll()    
                .requestMatchers(HttpMethod.PUT, "/checklist").permitAll()    
                .requestMatchers(HttpMethod.GET, "/checklist/ativo").permitAll()
                .requestMatchers(HttpMethod.GET, "/checklist/download").permitAll()
                .requestMatchers(HttpMethod.GET, "/avaliacao").permitAll()
                .requestMatchers(HttpMethod.GET, "/avaliacao/ativo/{ativoId}").permitAll()
                .requestMatchers(HttpMethod.POST, "/avaliacao").permitAll()
                //.requestMatchers(HttpMethod.GET, "/checkaprov").hasAnyRole("VALIDADOR", "ADMINISTRADOR")
                .requestMatchers(HttpMethod.GET, "/checkaprov").permitAll()
                //.requestMatchers(HttpMethod.POST, "/checkaprov").hasAnyRole("VALIDADOR", "ADMINISTRADOR")
                .requestMatchers(HttpMethod.POST, "/checkaprov").permitAll()
                //.requestMatchers(HttpMethod.POST, "/checkaprov/*").hasAnyRole("VALIDADOR", "ADMINISTRADOR")
                .requestMatchers(HttpMethod.POST, "/checkaprov/*").permitAll()
                //.requestMatchers(HttpMethod.PUT, "/checkaprov").hasAnyRole("VALIDADOR", "ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/checkaprov").permitAll()
                //.requestMatchers(HttpMethod.DELETE, "/checkaprov").hasAnyRole("VALIDADOR", "ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/checkaprov").permitAll()
                .requestMatchers(HttpMethod.GET, "/aprovador").permitAll()
                .requestMatchers(HttpMethod.GET, "/suporte").permitAll()
                .requestMatchers(HttpMethod.POST, "/suporte").permitAll()
                .requestMatchers(HttpMethod.PUT, "/suporte").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/suporte").permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
