package br.com.jose.sandbox.spring.security

import br.com.jose.sandbox.spring.entity.Authorities
import org.springframework.security.core.GrantedAuthority

class GrantedAuthorityImpl(private val authority: Authorities) : GrantedAuthority {

    override fun getAuthority(): String {
        return authority.authority
    }

    companion object {
        private const val serialVersionUID = -2575906197345202442L
    }

}