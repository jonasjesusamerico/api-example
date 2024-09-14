package br.com.jonasdev.api_wallet.application.web.transaction

import br.com.jonasdev.api_wallet.application.web.transaction.dto.TransactionRequestDto
import br.com.jonasdev.api_wallet.application.web.transaction.dto.TransactionResponseDto
import br.com.jonasdev.api_wallet.application.web.transaction.dto.fromDomain
import br.com.jonasdev.api_wallet.domain.transaction.TransactionDomain
import br.com.jonasdev.api_wallet.domain.transaction.service.TransactionService
import br.com.jonasdev.api_wallet.infrastructure.configuration.exception.CustomErrorControllerException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/transactions")
class TransactionController(private val service: TransactionService) {


    @PostMapping
    fun create(@RequestBody dto: TransactionRequestDto) {
        service.create(dto.toDomain())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<TransactionResponseDto> {
        val transactionDomain: Optional<TransactionDomain> = service.findById(id)

        if (transactionDomain.isPresent.not()) {
            throw CustomErrorControllerException(
                "The record with the provided ID does not exist.",
                HttpStatus.NOT_FOUND
            )
        }

        val dto = TransactionResponseDto.fromDomain(transactionDomain.get())
        return ResponseEntity.ok(dto)
    }


}


