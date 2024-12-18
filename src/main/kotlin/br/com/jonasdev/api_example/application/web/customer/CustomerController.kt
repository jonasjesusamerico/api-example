package br.com.jonasdev.api_example.application.web.customer

import br.com.jonasdev.api_example.application.web.customer.dto.CustomerRequest
import br.com.jonasdev.api_example.application.web.customer.dto.CustomerResponse
import br.com.jonasdev.api_example.application.web.customer.dto.fromDomain
import br.com.jonasdev.api_example.application.web.customer.dto.toDomain
import br.com.jonasdev.api_example.domain.representation.customer.CustomerDomainRepresentation
import br.com.jonasdev.api_example.domain.service.customer.CustomerService
import br.com.jonasdev.api_example.infrastructure.config.exception.CustomErrorControllerException
import br.com.jonasdev.api_example.library.pageable.InternPageableImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("api/v1/customer")
class CustomerController(private val service: CustomerService) {


    @PostMapping
    fun create(@RequestBody dto: Objects): ResponseEntity<CustomerResponse> {
//        service.create(dto.toDomain())
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerResponse> {
        val transactionDomain: Optional<CustomerDomainRepresentation> = service.findById(id)

        if (transactionDomain.isPresent.not()) {
            throw CustomErrorControllerException(
                "The record with the provided ID does not exist.",
                HttpStatus.NOT_FOUND
            )
        }

        val dto = CustomerResponse.fromDomain(transactionDomain.get())
        return ResponseEntity.ok(dto)
    }

    @GetMapping()
    fun findAll(
        @RequestParam(name = "limit", defaultValue = "20") limit: Int,
        @RequestParam(name = "offset", defaultValue = "0") offset: Int,
        @RequestParam(name = "sort", defaultValue = "id asc") sort: String
    ): ResponseEntity<InternPageableImpl<CustomerResponse>> {

        val page = service.findAll(InternPageableImpl<CustomerDomainRepresentation>(offset, limit, sort))

        val pageDomain = page.content()
            ?.map { t -> CustomerResponse.fromDomain(t) }

        return ResponseEntity.ok(
            InternPageableImpl<CustomerResponse>(
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
        @RequestBody dto: CustomerRequest
    ): ResponseEntity<CustomerResponse> {
        service.update(id, dto.toDomain())
        return ResponseEntity.accepted().build<CustomerResponse>()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<CustomerResponse> {
        service.delete(id)

        return ResponseEntity.noContent().build<CustomerResponse>()
    }

    @PatchMapping("/{id}")
    fun updateFields(
        @PathVariable id: Long,
        @RequestBody fieldValue: Map<String, Any>
    ): ResponseEntity<CustomerResponse> {
        service.updateFields(id, fieldValue)
        return ResponseEntity.accepted().build<CustomerResponse>()
    }


}


