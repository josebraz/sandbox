package br.com.jose.sandbox.spring.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.propertyeditors.CustomDateEditor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler
import org.springframework.session.web.http.HttpSessionIdResolver
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder
import java.text.SimpleDateFormat
import java.util.*

@EnableWebSecurity
class SecurityConfiguration @Autowired constructor(
        private val userDetailsService: UserDetailsServiceImpl
){
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(10)
    }

    @InitBinder
    private fun dateBinder(binder: WebDataBinder) { //The date format to parse or output your dates
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val editor = CustomDateEditor(dateFormat, true)
        binder.registerCustomEditor(Date::class.java, editor)
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService)
        authProvider.setPasswordEncoder(passwordEncoder())
        return authProvider
    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(authenticationProvider())
    }

    @Bean
    fun httpSessionIdResolver(): HttpSessionIdResolver {
        return CookieAndHeaderHttpSessionIdResolver()
    }

    @Configuration
    @Order(1)
    class ApiWebSecurityConfigurationAdapter : WebSecurityConfigurerAdapter() {
        @Throws(Exception::class)
        override fun configure(http: HttpSecurity) {
            http
                    .antMatcher(API_ANT_PATTERN)
                    .authorizeRequests()
                    .antMatchers("/api/users").fullyAuthenticated()
                    .and()
                        .httpBasic()
                    .and()
                        .csrf().disable()
                    .logout()
                        .logoutUrl("/api/users/logout")
                        .logoutSuccessHandler(HttpStatusReturningLogoutSuccessHandler(HttpStatus.NO_CONTENT))
                        .invalidateHttpSession(true)
        }
    }

    @Configuration
    class FormLoginWebSecurityConfigurationAdapter : WebSecurityConfigurerAdapter() {
        @Throws(Exception::class)
        override fun configure(http: HttpSecurity) {
            http
                    .authorizeRequests()
                    .antMatchers("/accessDenied").permitAll()
                    .antMatchers("/error").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/images/**").permitAll()
                    .antMatchers("/users").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login").permitAll()
                    .and()
                    .logout()
                    .logoutUrl("/users/logout")
        }
    }

    companion object {
        const val API_ANT_PATTERN = "/api/**"
    }
}