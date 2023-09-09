package com.netosantos.springsecurity.controller.role

import com.netosantos.springsecurity.controller.role.dto.SaveRoleRequestDto
import com.netosantos.springsecurity.entity.RoleEntity
import com.netosantos.springsecurity.service.role.RoleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("role")
class RoleController(private val roleService: RoleService) {

    @GetMapping("")
    fun getRoles(): List<RoleEntity> {
        return roleService.findAllRoles()
    }
    @PostMapping("")
    fun saveRoles(@RequestBody body: SaveRoleRequestDto): String {
        return roleService.saveRole(body.roleName)
    }
}
