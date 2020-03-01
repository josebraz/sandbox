package br.com.jose.sandbox.spring.persistence

import br.com.jose.sandbox.spring.entity.Authorities
import br.com.jose.sandbox.spring.entity.AuthoritiesPK
import br.com.jose.sandbox.spring.entity.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthoritiesRepository : JpaRepository<Authorities, AuthoritiesPK> {
    fun findByUser(user: Users?): List<Authorities>
}