package com.little.xmall.config;

import jakarta.servlet.http.Cookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


//    private final CustomAuthenticationProvider customAuthenticationProvider;
//
//    public SecurityConfig(CustomAuthenticationProvider customAuthenticationProvider) {
//        this.customAuthenticationProvider = customAuthenticationProvider;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .anyRequest().permitAll());
        http.csrf().disable();


        http.cors();
        return http.build();
    }
//                .requestMatchers("/user/register","/test/login").permitAll()
//                .anyRequest().authenticated())
//
//                .formLogin((form) -> form
//                        .loginPage("/test/login")
//                        .permitAll() // 允许所有人访问登录页面
//                );
//
//        http.csrf().disable();
//
//
//        http.cors();
//        return http.build();
//    }
//
//
//
//
//
//
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService)
//            throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .authenticationProvider(customAuthenticationProvider)
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder)
//                .and()
//                .build();
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
