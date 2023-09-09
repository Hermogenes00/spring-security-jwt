package com.netosantos.springsecurity.service.client.dto

import com.netosantos.springsecurity.entity.ClientEntity
import java.util.UUID
data class CreateClientDto(var name:String, var email:String, var password:String)

fun CreateClientDto.toClientEntity():ClientEntity{
    return ClientEntity(UUID.randomUUID().toString(),this.name,this.email,this.password)
}