package br.com.jose.sandbox.spring.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class HomeController {

    private val _log: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun index(): String {
        return "index";
    }

}