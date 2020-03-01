package br.com.jose.sandbox.spring.entity

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class AuthoritiesPK(
    @Column(nullable = false)
    private var login: String,
    @Column(nullable = false)
    private var authority: String
): Serializable