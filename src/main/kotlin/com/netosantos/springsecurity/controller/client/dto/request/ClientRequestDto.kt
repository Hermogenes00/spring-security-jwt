package com.netosantos.springsecurity.controller.client.dto.request


import com.netosantos.springsecurity.entity.ClientEntity
import java.util.UUID

data class UserRequestDto(val name: String, val email: String, val password: String)

fun UserRequestDto.clientRequestDtoToUserEntity(): ClientEntity {
    return ClientEntity(UUID.randomUUID().toString(), name, email, password)
}