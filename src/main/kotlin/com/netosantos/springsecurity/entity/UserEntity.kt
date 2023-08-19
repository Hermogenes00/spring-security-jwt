package com.netosantos.springsecurity.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.context.annotation.Primary

@Entity(name = "\"user\"")
data class UserEntity(@Id

                      @Column(length = 255)
                      var id: String,
                      @Column(length = 25)
                      var name: String,
                      @Column(length = 25)
                      var email: String,
                      @Column(length = 100)
                      var password: String) {
    constructor() : this("", "", "", "")
}