package com.netosantos.springsecurity.controller.client

import com.netosantos.springsecurity.controller.client.dto.request.UserRequestDto
import com.netosantos.springsecurity.controller.client.dto.request.ClientUpdateRequest
import com.netosantos.springsecurity.entity.ClientEntity
import com.netosantos.springsecurity.service.client.ClientService
import com.netosantos.springsecurity.service.client.dto.CreateClientDto
import com.netosantos.springsecurity.service.client.dto.ClientUpdateDto
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("client")
class ClientController(private val clientService: ClientService, private val passwordEncoder: PasswordEncoder) {
    @GetMapping("")
    fun getUsers(): MutableList<ClientEntity> {
        return clientService.findUsers()
    }

    @PostMapping("")
    fun createUser(@RequestBody body: UserRequestDto): String {
        val createUserObj = CreateClientDto(body.name, body.email, passwordEncoder.encode(body.password))
        val userId = clientService.addUser(createUserObj)
        return userId
    }

    @GetMapping("/{clientId}")
    fun getUserById(@PathVariable clientId: String): ClientEntity {
        return clientService.findUserById(clientId)
    }

    @GetMapping("/findByEmail/{email}")
    fun getUserByEmail(@PathVariable email: String): ClientEntity? {
        return clientService.findUserByEmail(email)
    }

    @PutMapping("/{clientId}")
    fun updateUserById(@PathVariable clientId: String, @RequestBody body: ClientUpdateRequest): ClientEntity {
        return clientService.updateUserById(clientId, ClientUpdateDto(body.name, body.email, body.roles))
    }
}