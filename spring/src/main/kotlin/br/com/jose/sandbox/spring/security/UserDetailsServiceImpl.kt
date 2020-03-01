package br.com.jose.sandbox.spring.security

import br.com.jose.sandbox.spring.persistence.AuthoritiesRepository
import br.com.jose.sandbox.spring.persistence.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class UserDetailsServiceImpl @Autowired constructor(
        private val userRepository: UserRepository,
        private val authoritiesRepository: AuthoritiesRepository
) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findById(username)
        if (!user.isPresent) {
            throw UsernameNotFoundException(username)
        }
        return UserPrincipal(user.get(), authoritiesRepository.findByUser(user.get()))
    }

}