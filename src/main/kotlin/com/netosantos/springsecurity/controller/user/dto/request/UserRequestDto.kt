package com.netosantos.springsecurity.controller.user.dto.request


import com.netosantos.springsecurity.entity.UserEntity
import java.util.UUID

data class UserRequestDto(val name: String, val email: String, val password: String)

fun UserRequestDto.userRequestDtoToUserEntity(): UserEntity {
    return UserEntity(UUID.randomUUID().toString(), name, email, password)
}