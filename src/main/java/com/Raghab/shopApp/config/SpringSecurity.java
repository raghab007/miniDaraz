package com.Raghab.shopApp.config;
import com.Raghab.shopApp.service.UserDetailsServiceImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity  {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/user/**").authenticated()
                        .requestMatchers("/cart/**").authenticated()
                        // Require authentication for URLs under /journal/ and /user/
                        .requestMatchers("/admin/**").permitAll()  .anyRequest().permitAll()          // Require ADMIN role for URLs under /admin/.anyRequest().permitAll()                         // Require authentication for all other requests
                );
        // All other URLs require authentication
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImplementation();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}





