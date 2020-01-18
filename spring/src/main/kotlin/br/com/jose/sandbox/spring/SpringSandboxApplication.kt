package br.com.jose.sandbox.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringSandboxApplication {

}

fun main(args: Array<String>) {
	runApplication<SpringSandboxApplication>(*args)
}
