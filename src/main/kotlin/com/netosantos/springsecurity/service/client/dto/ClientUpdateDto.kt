package com.netosantos.springsecurity.service.client.dto

import com.netosantos.springsecurity.entity.RoleEntity

data class ClientUpdateDto(var name:String, var email:String, var roles:Set<RoleEntity>?)