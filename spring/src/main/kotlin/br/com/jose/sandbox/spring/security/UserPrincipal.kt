package br.com.jose.sandbox.spring.security

import br.com.jose.sandbox.spring.entity.Authorities
import br.com.jose.sandbox.spring.entity.Users
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

class UserPrincipal constructor(
        private val user: Users?,
        authorities: List<Authorities>
) : UserDetails {

    private var authoritiesGranted: Collection<GrantedAuthority>

    init {
        this.authoritiesGranted = authorities.stream().map { authority: Authorities -> GrantedAuthorityImpl(authority) }.collect(Collectors.toList())
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authoritiesGranted
    }

    override fun getPassword(): String {
        return user!!.password
    }

    override fun getUsername(): String {
        return user!!.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}