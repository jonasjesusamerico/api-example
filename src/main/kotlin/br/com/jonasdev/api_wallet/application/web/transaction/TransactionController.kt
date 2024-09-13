package br.com.jonasdev.api_wallet.application.web.transaction

import br.com.jonasdev.api_wallet.domain.transaction.service.TransactionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transactions")
class TransactionController(private val service : TransactionService) {


    @PostMapping
    fun create(@RequestBody dto : TransactionInputDto) {
        service.create(dto.toDomain())
    }


}