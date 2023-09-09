package com.netosantos.springsecurity.repository

import com.netosantos.springsecurity.entity.ClientEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository : JpaRepository<ClientEntity, String> {
    fun findByEmail(email: String): ClientEntity?
}