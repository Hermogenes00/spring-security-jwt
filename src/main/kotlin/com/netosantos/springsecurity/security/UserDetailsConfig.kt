package com.netosantos.springsecurity.security

import com.netosantos.springsecurity.entity.ClientEntity
import com.netosantos.springsecurity.repository.ClientRepository
import org.springframework.http.HttpStatus
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException

@Component
class UserDetailsConfig(private val clientRepository: ClientRepository) : UserDetailsService {
    override fun loadUserByUsername(email: String?): UserDetails {
        val client = clientRepository.findByEmail(email ?: "")
                ?: throw HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE)
        return User(client.email, client.password, loadAuthoritiesByUser(client))
    }

    private fun loadAuthoritiesByUser(client: ClientEntity): List<SimpleGrantedAuthority> {
        val roles = client.roles ?: throw HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE)
        return roles.map {
            SimpleGrantedAuthority(it.name)
        }
    }
}