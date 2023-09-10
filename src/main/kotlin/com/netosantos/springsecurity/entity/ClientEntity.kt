package com.netosantos.springsecurity.entity

import jakarta.persistence.*

@Entity(name = "client")
data class ClientEntity(@Id

                        @Column(length = 255)
                        var id: String,
                        @Column(length = 25)
                        var name: String,
                        @Column(length = 25)
                        var email: String,
                        @Column(length = 100)
                        var password: String,
                        @OneToMany(fetch = FetchType.EAGER)
                        var roles: MutableSet<RoleEntity>? = null
) {
    constructor() : this("", "", "", "", null)
}