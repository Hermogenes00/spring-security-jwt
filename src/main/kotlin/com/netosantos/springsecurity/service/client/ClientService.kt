package com.netosantos.springsecurity.service.client

import com.netosantos.springsecurity.entity.RoleEntity
import com.netosantos.springsecurity.entity.ClientEntity
import com.netosantos.springsecurity.repository.RoleRepository
import com.netosantos.springsecurity.repository.ClientRepository
import com.netosantos.springsecurity.service.client.dto.CreateClientDto
import com.netosantos.springsecurity.service.client.dto.ClientUpdateDto
import com.netosantos.springsecurity.service.client.dto.toClientEntity
import org.springframework.stereotype.Service

@Service
class ClientService(private val clientRepository: ClientRepository, private val roleRepository: RoleRepository) {

    fun addUser(createClientDto: CreateClientDto): String {
        val response = clientRepository.save(createClientDto.toClientEntity())
        return response.id
    }

    fun findUsers(): MutableList<ClientEntity> {
        return clientRepository.findAll()
    }

    fun findUserById(userId: String): ClientEntity {
        return clientRepository.findById(userId).orElseThrow()
    }

    fun findUserByEmail(email: String): ClientEntity? {
        return clientRepository.findByEmail(email)
    }

    fun updateUserById(userId: String, userUpdate: ClientUpdateDto): ClientEntity {
        println("got in updateUserById")
        val userEntity = findUserById(userId)
        userEntity.name = userUpdate.name
        userEntity.email = userUpdate.email

        println(userEntity)
        userUpdate.roles?.let {
            if (it.isNotEmpty()) {
                val filteredRoles = validateRoles(it.toList())
                println("filteredRoles $filteredRoles")
                userEntity.roles = filteredRoles.toMutableSet()

                println("userEntity.roles ${userEntity.roles}")
            }
        }

        clientRepository.save(userEntity)

        return userEntity
    }

    fun validateRoles(roles: List<RoleEntity>): List<RoleEntity> {
        return roles.filter { roleRepository.existsByName(it.name ?: "") }
    }
}