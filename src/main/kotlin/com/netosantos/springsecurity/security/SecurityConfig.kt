package com.netosantos.springsecurity.security

import com.netosantos.springsecurity.repository.ClientRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig(val jwtService: JwtService, val clientRepository: ClientRepository) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf {
            it.disable()
        }.authorizeHttpRequests {
            it.requestMatchers(HttpMethod.POST, "/auth").permitAll()
            it.requestMatchers(HttpMethod.DELETE).authenticated()
            it.requestMatchers(HttpMethod.POST, "/client").permitAll()
            it.requestMatchers(HttpMethod.POST).authenticated()
            it.anyRequest().authenticated()
        }.addFilterBefore(JwtSecurityFilter(jwtService,clientRepository), UsernamePasswordAuthenticationFilter::class.java)
                .httpBasic { }

        return http.build()
    }

    @Bean
    fun bCrypt(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }
}