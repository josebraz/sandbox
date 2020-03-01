package br.com.jose.sandbox.spring.entity

import java.io.Serializable
import javax.persistence.*

@Entity
data class Authorities(
        @EmbeddedId
    var pk: AuthoritiesPK,
        @ManyToOne
    @JoinColumn(name = "login", insertable = false, updatable = false)
    val user: Users? = null,
        @Column(nullable = false, insertable = false, updatable = false)
    val authority: String
): Serializable