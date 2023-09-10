package com.netosantos.springsecurity.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "role")
data class RoleEntity(@Id @Column(length = 255) var name: String?){
    constructor():this(null)
}