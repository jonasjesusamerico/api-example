package br.com.jonasdev.api_example.infrastructure.config.exception

import org.springframework.http.HttpStatus


interface ErrorMessage {

    fun getCode(): Int

    fun getStatus(): HttpStatus

    fun getMessage(): String?

}
