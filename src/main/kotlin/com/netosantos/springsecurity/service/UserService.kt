package com.netosantos.springsecurity.service

import com.netosantos.springsecurity.controller.user.dto.request.userRequestDtoToUserEntity
import com.netosantos.springsecurity.entity.UserEntity
import com.netosantos.springsecurity.repository.UserRepository
import com.netosantos.springsecurity.service.dto.CreateUserDto
import com.netosantos.springsecurity.service.dto.UserUpdateDto
import com.netosantos.springsecurity.service.dto.toUserEntity
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun addUser(createUserDto: CreateUserDto): String {
        val response = userRepository.save(createUserDto.toUserEntity())
        return response.id
    }

    fun findUsers(): MutableList<UserEntity> {
        return userRepository.findAll()
    }

    fun findUserById(userId: String): UserEntity {
        return userRepository.findById(userId).orElseThrow()
    }

    fun findUserByEmail(email: String): UserEntity? {
        return userRepository.findByEmail(email)
    }

    fun updateUserById(userId:String, userUpdate:UserUpdateDto ):UserEntity{
        val userEntity = findUserById(userId)

        userEntity.name = userUpdate.name
        userEntity.email = userUpdate.email

        userRepository.save(userEntity)

        return userEntity
    }
}