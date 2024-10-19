package br.com.jonasdev.api_wallet.application.web.transaction

import br.com.jonasdev.api_wallet.application.web.transaction.dto.TransactionRequest
import br.com.jonasdev.api_wallet.application.web.transaction.dto.TransactionResponse
import br.com.jonasdev.api_wallet.application.web.transaction.dto.fromDomain
import br.com.jonasdev.api_wallet.application.web.transaction.dto.toDomain
import br.com.jonasdev.api_wallet.domain.representation.transaction.TransactionDomainRepresentation
import br.com.jonasdev.api_wallet.domain.service.transaction.TransactionService
import br.com.jonasdev.api_wallet.infrastructure.configuration.exception.CustomErrorControllerException
import br.com.jonasdev.api_wallet.library.pageable.InternPageableImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/transactions")
class TransactionController(private val service: TransactionService) {


    @PostMapping
    fun create(@RequestBody dto: TransactionRequest): ResponseEntity<TransactionResponse> {
        service.create(dto.toDomain())
        return ResponseEntity.ok().build<TransactionResponse>()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<TransactionResponse> {
        val transactionDomain: Optional<TransactionDomainRepresentation> = service.findById(id)

        if (transactionDomain.isPresent.not()) {
            throw CustomErrorControllerException(
                "The record with the provided ID does not exist.",
                HttpStatus.NOT_FOUND
            )
        }

        val dto = TransactionResponse().fromDomain(transactionDomain.get())
        return ResponseEntity.ok(dto)
    }

    @GetMapping()
    fun findAll(
        @RequestParam(name = "limit", defaultValue = "20") limit: Int,
        @RequestParam(name = "offset", defaultValue = "0") offset: Int,
        @RequestParam(name = "sort", defaultValue = "id asc") sort: String
    ): ResponseEntity<InternPageableImpl<TransactionResponse>> {

        val page = service.findAll(InternPageableImpl<TransactionDomainRepresentation>(offset, limit, sort))

        val pageDomain = page.content()
            ?.map { t -> TransactionResponse().fromDomain(t) }

        return ResponseEntity.ok(
            InternPageableImpl<TransactionResponse>(
                pageDomain,
                page.pageNumber(),
                page.pageSize(),
                page.totalElements(),
                page.sort()
            )
        )
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody dto: TransactionRequest
    ): ResponseEntity<TransactionResponse> {
        service.update(id, dto.toDomain())
        return ResponseEntity.accepted().build<TransactionResponse>()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<TransactionResponse> {
        service.delete(id)

        return ResponseEntity.noContent().build<TransactionResponse>()
    }

    @PatchMapping("/{id}")
    fun updateFields(
        @PathVariable id: Long,
        @RequestBody fieldValue: Map<String, Any>
    ): ResponseEntity<TransactionResponse> {
        service.updateFields(id, fieldValue)
        return ResponseEntity.accepted().build<TransactionResponse>()
    }


}


