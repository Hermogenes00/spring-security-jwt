package com.netosantos.springsecurity.service.dto

import com.netosantos.springsecurity.entity.UserEntity
import java.util.UUID
data class CreateUserDto(var name:String,var email:String, var password:String)

fun CreateUserDto.toUserEntity():UserEntity{
    return UserEntity(UUID.randomUUID().toString(),this.name,this.email,this.password)
}