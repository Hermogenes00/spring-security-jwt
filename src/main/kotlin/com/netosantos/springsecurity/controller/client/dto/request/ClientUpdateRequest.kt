package com.netosantos.springsecurity.controller.client.dto.request

import com.netosantos.springsecurity.entity.RoleEntity

data class ClientUpdateRequest(var name:String, var email:String, var roles:Set<RoleEntity>?)
