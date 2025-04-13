package org.enset.hopital.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

//    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user").password(passwordEncoder.encode( "1234")).roles("USER").build(),
                User.withUsername("user1").password(passwordEncoder.encode( "1234")).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder.encode( "1234")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode( "1234")).roles("ADMIN","USER").build()

        );
    }

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll();
        http.rememberMe().rememberMeParameter("remember-me");
        http.authorizeHttpRequests()
                .requestMatchers("/webjars/**", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/login", "/logout").permitAll()
//                .requestMatchers("/user/**").hasRole("USER")
//                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/notAuthorized");

        return http.build();
    }

}
