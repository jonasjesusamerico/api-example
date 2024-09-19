package br.com.jonasdev.api_wallet.application.web.transaction

import br.com.jonasdev.api_wallet.application.web.transaction.dto.TransactionRequestDto
import br.com.jonasdev.api_wallet.application.web.transaction.dto.TransactionResponseDto
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
    fun create(@RequestBody dto: TransactionRequestDto): ResponseEntity<TransactionResponseDto> {
        service.create(dto.toDomain())
        return ResponseEntity.ok().build<TransactionResponseDto>()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<TransactionResponseDto> {
        val transactionDomain: Optional<TransactionDomainRepresentation> = service.findById(id)

        if (transactionDomain.isPresent.not()) {
            throw CustomErrorControllerException(
                "The record with the provided ID does not exist.",
                HttpStatus.NOT_FOUND
            )
        }

        val dto = TransactionResponseDto().fromDomain(transactionDomain.get())
        return ResponseEntity.ok(dto)
    }

    @GetMapping()
    fun findAll(
        @RequestParam(name = "limit", defaultValue = "20") limit: Int,
        @RequestParam(name = "offset", defaultValue = "0") offset: Int,
        @RequestParam(name = "sort", defaultValue = "id asc") sort: String
    ): ResponseEntity<InternPageableImpl<TransactionResponseDto>> {

        val page = service.findAll(InternPageableImpl<TransactionDomainRepresentation>(offset, limit, sort))

        val pageDomain = page.content()
            ?.map { t -> TransactionResponseDto().fromDomain(t) }

        return ResponseEntity.ok(
            InternPageableImpl<TransactionResponseDto>(
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
        @RequestBody dto: TransactionRequestDto
    ): ResponseEntity<TransactionResponseDto> {
        service.update(id, dto.toDomain())
        return ResponseEntity.accepted().build<TransactionResponseDto>()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<TransactionResponseDto> {
        service.delete(id)

        return ResponseEntity.noContent().build<TransactionResponseDto>()
    }

    @PatchMapping("/{id}")
    fun updateFields(
        @PathVariable id: Long,
        @RequestBody fieldValue: Map<String, Any>
    ): ResponseEntity<TransactionResponseDto> {
        service.updateFields(id, fieldValue)
        return ResponseEntity.accepted().build<TransactionResponseDto>()
    }


}


