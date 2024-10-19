package br.com.jonasdev.api_wallet.infrastructure.configuration.exception

import org.springframework.http.HttpStatus

class CustomErrorControllerException(
    message: String,
    val httpStatus: HttpStatus
) : RuntimeException(message)
