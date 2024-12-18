package br.com.jonasdev.api_example.domain.repository.customer

import br.com.jonasdev.api_example.domain.configuration.pageable.InternPageable
import br.com.jonasdev.api_example.domain.representation.customer.CustomerDomainRepresentation
import br.com.jonasdev.api_example.library.pageable.InternPageableImpl
import java.util.*

interface CustomerRepository {

    fun create(transaction: CustomerDomainRepresentation)

    fun findById(id: Long): Optional<CustomerDomainRepresentation>

    fun findAll(pageable: InternPageable<CustomerDomainRepresentation>): InternPageableImpl<CustomerDomainRepresentation>

    fun update(id: Long, transaction: CustomerDomainRepresentation)

    fun delete(id: Long)

}
