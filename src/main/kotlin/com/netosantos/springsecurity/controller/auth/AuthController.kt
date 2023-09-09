package com.netosantos.springsecurity.controller.auth

import com.netosantos.springsecurity.controller.auth.dto.AuthRequestDto
import com.netosantos.springsecurity.security.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthController(val manager: AuthenticationManager,val jwtService: JwtService) {

    @PostMapping()
    fun getHello(@RequestBody body: AuthRequestDto): String {

        val authentication = UsernamePasswordAuthenticationToken(body.user, body.password)
        manager.authenticate(authentication)

        println("isAuthenticated?? ${authentication.isAuthenticated}")

        val jwtToken = jwtService.createToken(authentication.name)

        return jwtToken
    }
}