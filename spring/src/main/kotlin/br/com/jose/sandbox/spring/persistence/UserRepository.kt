package br.com.jose.sandbox.spring.persistence

import br.com.jose.sandbox.spring.entity.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<Users, String> {

}