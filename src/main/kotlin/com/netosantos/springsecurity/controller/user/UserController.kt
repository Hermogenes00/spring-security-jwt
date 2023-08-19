package com.netosantos.springsecurity.controller.user

import com.netosantos.springsecurity.controller.user.dto.request.UserRequestDto
import com.netosantos.springsecurity.controller.user.dto.request.UserUpdateRequest
import com.netosantos.springsecurity.controller.user.dto.request.userRequestDtoToUserEntity
import com.netosantos.springsecurity.entity.UserEntity
import com.netosantos.springsecurity.repository.UserRepository
import com.netosantos.springsecurity.service.UserService
import com.netosantos.springsecurity.service.dto.CreateUserDto
import com.netosantos.springsecurity.service.dto.UserUpdateDto
import org.hibernate.annotations.Parameter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user")
class UserController(private val userService: UserService) {
    @GetMapping("")
    fun getUsers(): MutableList<UserEntity> {
        return userService.findUsers()
    }

    @PostMapping("")
    fun createUser(@RequestBody body: UserRequestDto): String {
        val createUserObj = CreateUserDto(body.name, body.email, body.password)
        val userId = userService.addUser(createUserObj)
        return userId
    }

    @GetMapping("/{userId}")
    fun getUserById(@PathVariable userId: String): UserEntity {
        return userService.findUserById(userId)
    }

    @GetMapping("/findByEmail/{email}")
    fun getUserByEmail(@PathVariable email: String): UserEntity? {
        return userService.findUserByEmail(email)
    }

    @PutMapping("/{userId}")
    fun updateUserById(@PathVariable userId: String, @RequestBody body: UserUpdateRequest) :UserEntity{
        return userService.updateUserById(userId, UserUpdateDto(body.name, body.email))
    }
}