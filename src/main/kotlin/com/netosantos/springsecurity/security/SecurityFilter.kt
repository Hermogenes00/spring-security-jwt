package com.netosantos.springsecurity.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class SecurityFilter(private val jwtTokenService: TokenService) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token = getTokenFromRequest(request)

        if (token !== null) {

            val userName = jwtTokenService.validateToken(token)

            val authentication = UsernamePasswordAuthenticationToken(userName, null,
                    listOf(
                            SimpleGrantedAuthority("ADMIN"),
                            SimpleGrantedAuthority("USER"))
            )

            SecurityContextHolder.getContext().authentication = authentication
        }


        filterChain.doFilter(request, response)

    }

    private fun getTokenFromRequest(request: HttpServletRequest): String? {
        var token: String? = null;

        request.getHeader("authorization")?.let {
            token = it.replace("Bearer ", "")
        }
        return token
    }
}