package com.netosantos.springsecurity.repository

import com.netosantos.springsecurity.entity.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository:JpaRepository<RoleEntity,String> {
    fun existsByName(name:String):Boolean
}