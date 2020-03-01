package br.com.jose.sandbox.spring.entity

import java.io.Serializable
import java.util.*
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Users (
        @Id
        var username: String,
        var password: String,
        var name: String,
        @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
        var authorities: List<Authorities> = ArrayList()
) : Serializable