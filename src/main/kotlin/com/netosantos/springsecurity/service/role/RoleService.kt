package com.netosantos.springsecurity.service.role

import com.netosantos.springsecurity.entity.RoleEntity
import com.netosantos.springsecurity.repository.RoleRepository
import org.springframework.stereotype.Service

@Service
class RoleService(private val roleRepository: RoleRepository) {
    fun findAllRoles(): List<RoleEntity> = roleRepository.findAll();

    fun saveRole(roleName:String):String{
        val roleEntity = RoleEntity(roleName)
        roleRepository.save(roleEntity)
        return roleName
    }

    fun existsByName(roleName: String):Boolean{
        return roleRepository.existsByName(roleName)
    }
}