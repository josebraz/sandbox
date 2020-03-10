package br.com.jose.sandbox.spring.entity

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
data class Users (
        @Id @GeneratedValue var username : String,
        var password: String,
        var name: String,
        @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
        var authorities: List<Authorities> = ArrayList()
) : Serializable