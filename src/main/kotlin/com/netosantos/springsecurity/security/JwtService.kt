package com.netosantos.springsecurity.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit

@Component
class JwtService {
    companion object {
        private const val MY_SECRET = "my-secret-here"
        private val ALGORITHM = Algorithm.HMAC256(MY_SECRET)
    }


    fun createToken(userName: String): String {
        val token = JWT
                .create()
                .withSubject(userName)
                .withIssuer("my-project")
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(30L, ChronoUnit.DAYS))
                .sign(ALGORITHM)

        return token
    }

    fun validateToken(token: String): String {
        val decoded = JWT.decode(token)
        return decoded.subject
    }
}