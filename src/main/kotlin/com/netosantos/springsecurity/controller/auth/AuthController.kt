package com.netosantos.springsecurity.controller.auth

import com.netosantos.springsecurity.controller.auth.dto.LoginDto
import com.netosantos.springsecurity.controller.auth.dto.RegisterDto
import com.netosantos.springsecurity.security.TokenService
import org.apache.coyote.Response
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/auth")
class AuthController(
        private val authenticationManager: AuthenticationManager,
        private val tokenService: TokenService,
        private val bCrypt: BCrypt
) {

    @GetMapping("/login")
    fun login(@RequestBody @Validated loginDto: LoginDto): ResponseEntity<Any> {
        val manager = UsernamePasswordAuthenticationToken(loginDto.user, loginDto.password)

        val token = tokenService.createToken(manager.principal as String)
        authenticationManager.authenticate(manager);

        return ResponseEntity.ofNullable(token);
    }

//
//    @PostMapping("/register")
//    fun register(@RequestBody @Validated registerDto:RegisterDto):ResponseEntity<Any>{
//    //Conectar no banco e criar tabela de User
//    //Fazer o bean do UserDetailService
//    //Realizar encode do password, e salvar no banco.
//    }
}