package com.netosantos.springsecurity.security

import com.netosantos.springsecurity.entity.ClientEntity
import com.netosantos.springsecurity.repository.ClientRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtSecurityFilter(val jwtService: JwtService, val clientRepository: ClientRepository) : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

        val headerToken = getTokenByHeader(request.getHeader("authorization"))

        if (headerToken.isNotEmpty()) {
            val decodedJwt = jwtService.validateToken(headerToken)
            val client = clientRepository.findByEmail(decodedJwt)
            val authentication = UsernamePasswordAuthenticationToken(client!!.email, null, credentialsAuthorityMapper(client))

            SecurityContextHolder.getContext().authentication = authentication

        }


        filterChain.doFilter(request, response)
    }

    private fun getTokenByHeader(authorizationHeader: String?): String {

        return authorizationHeader?.replace("Bearer ", "") ?: ""
    }

    private fun credentialsAuthorityMapper(clientEntity: ClientEntity): List<GrantedAuthority> {
        return clientEntity.roles?.asSequence()?.map { SimpleGrantedAuthority(it.name) }?.toList()
                ?: listOf(GrantedAuthority { "" })
    }

}