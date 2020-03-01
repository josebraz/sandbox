package br.com.jose.sandbox.spring.security

import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.session.web.http.CookieHttpSessionIdResolver
import org.springframework.session.web.http.HeaderHttpSessionIdResolver
import org.springframework.session.web.http.HttpSessionIdResolver
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CookieAndHeaderHttpSessionIdResolver : HttpSessionIdResolver {
    private val headerResolver: HeaderHttpSessionIdResolver = HeaderHttpSessionIdResolver.xAuthToken()
    private val cookieResolver: CookieHttpSessionIdResolver = CookieHttpSessionIdResolver()
    private val apiMatcher = AntPathRequestMatcher(SecurityConfiguration.API_ANT_PATTERN)

    override fun resolveSessionIds(request: HttpServletRequest?): List<String> {
        return if (apiMatcher.matches(request)) {
            headerResolver.resolveSessionIds(request)
        } else {
            cookieResolver.resolveSessionIds(request)
        }
    }

    override fun setSessionId(request: HttpServletRequest?, response: HttpServletResponse?, sessionId: String?) {
        if (apiMatcher.matches(request)) {
            headerResolver.setSessionId(request, response, sessionId)
        } else {
            cookieResolver.setSessionId(request, response, sessionId)
        }
    }

    override fun expireSession(request: HttpServletRequest?, response: HttpServletResponse?) {
        if (apiMatcher.matches(request)) {
            headerResolver.expireSession(request, response)
        } else {
            cookieResolver.expireSession(request, response)
        }
    }
}