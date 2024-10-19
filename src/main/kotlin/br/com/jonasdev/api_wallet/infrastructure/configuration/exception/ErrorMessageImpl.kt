package br.com.jonasdev.api_wallet.infrastructure.configuration.exception

import org.springframework.http.HttpStatus

class ErrorMessageImpl(
    private val message: String? = "",
    private val httpStatus: HttpStatus
) : ErrorMessage {
    override fun getCode(): Int {
        return httpStatus.value();
    }

    override fun getStatus(): HttpStatus {
        return httpStatus
    }

    override fun getMessage(): String? {
        return message
    }
}
