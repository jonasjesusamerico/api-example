package br.com.jonasdev.api_example.infrastructure.config.exception

import org.springframework.http.HttpStatus

class CustomErrorControllerException(
    message: String,
    val httpStatus: HttpStatus
) : RuntimeException(message)
