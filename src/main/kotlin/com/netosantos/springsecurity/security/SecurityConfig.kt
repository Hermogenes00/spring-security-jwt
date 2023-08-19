package com.netosantos.springsecurity.security

import jakarta.servlet.FilterChain
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(private val securityFilter: SecurityFilter) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf {
            it.disable()
        }.authorizeHttpRequests {
//            it.requestMatchers("main/names").authenticated()
            it.requestMatchers("/auth/login").permitAll()
            it.anyRequest().permitAll()
        }.sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }.addFilterBefore(securityFilter,UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun passwordEncoder(): BCrypt = BCrypt()

    @Bean
    fun users(): UserDetailsService {
        val users = User.withDefaultPasswordEncoder()
        val commomUser = users.username("joao")
                .password("123")
                .roles("USER")
                .build()

        val adminUser = users.username("maria")
                .password("123")
                .roles("USER", "ADMIN")
                .build()



        return InMemoryUserDetailsManager(commomUser, adminUser)
    }
}