package com.netosantos.springsecurity.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit

@Service
class TokenService {

//    @Value("\${jwt.secret.key}")
    val jwtSecretKey: String = "my-secret-key";
    val algorithm: Algorithm = Algorithm.HMAC256(jwtSecretKey)

    fun createToken(userName:String): String {

        val token = JWT.create()
                .withIssuer("my-project")
                .withSubject(userName)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(30L, ChronoUnit.DAYS))
                .sign(algorithm)
        return token;
    }


    fun validateToken(token: String): String? {
        return JWT.require(algorithm)
                .withIssuer("my-project")
                .build()
                .verify(token)
                .subject
    }

}