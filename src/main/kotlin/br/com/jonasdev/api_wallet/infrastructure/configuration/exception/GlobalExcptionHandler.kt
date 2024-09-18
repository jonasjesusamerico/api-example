package br.com.jonasdev.api_wallet.infrastructure.configuration.exception

import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class GlobalExcptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleDefaultException(ex: Exception): ResponseEntity<String> {
        var cause = ""
        if (ex.cause != null) {
            cause = "Cause: " + ex.cause!!.message
        }

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ex.message + cause)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleDefaultException(ex: EntityNotFoundException): ResponseEntity<String> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ex.message)
    }

    @ExceptionHandler(CustomErrorControllerException::class)
    fun handleCustomException(ex: CustomErrorControllerException): ResponseEntity<ErrorMessage> {
        return ResponseEntity
            .status(ex.getStatusCode())
            .body(ErrorMessageImpl(ex.message, ex.getStatusCode()))
    }

}