package br.com.jose.sandbox.spring.api

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class UserApi {

    private val _log: Logger = LoggerFactory.getLogger(UserApi::class.java)

	@GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
	fun get(): ResponseEntity<String>? {
		_log.debug("GET CALLED")
		return ResponseEntity.ok("GET CALLED")
	}
}