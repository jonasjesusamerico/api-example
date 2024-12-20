package br.com.jonasdev.api_example.domain.service.customer

import br.com.jonasdev.api_example.domain.configuration.pageable.InternPageable
import br.com.jonasdev.api_example.domain.model.customer.CustomerDomain
import br.com.jonasdev.api_example.domain.repository.customer.CustomerRepository
import br.com.jonasdev.api_example.domain.representation.customer.CustomerDomainRepresentation
import br.com.jonasdev.api_example.library.pageable.InternPageableImpl
import jakarta.persistence.EntityNotFoundException
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

class CustomerDomainService(private val repository: CustomerRepository) : CustomerService {

    override fun create(toDomain: CustomerDomainRepresentation) {
        repository.create(toDomain)
    }

    override fun findById(id: Long): Optional<CustomerDomainRepresentation> {
        return repository.findById(id)
    }

    override fun findAll(pageable: InternPageable<CustomerDomainRepresentation>): InternPageableImpl<CustomerDomainRepresentation> {
        return repository.findAll(pageable)
    }

    override fun update(id: Long, transaction: CustomerDomainRepresentation) {
        return repository.update(id, transaction)
    }

    override fun delete(id: Long) {
        return repository.delete(id)
    }

    override fun updateFields(id: Long, fieldValue: Map<String, Any>) {
        val transaction = repository.findById(id)
            .orElseThrow { EntityNotFoundException("Transaction with id $id not found.") }

        fieldValue.forEach { (key, value) ->
            val property = CustomerDomain::class.memberProperties
                .find { it.name == key }
                ?: throw IllegalArgumentException("Unknown field: $key")

            if (property is kotlin.reflect.KMutableProperty<*>) {
                property.isAccessible = true

                val propertyType = property.returnType.classifier as? KClass<*>
                if (propertyType != value::class) {
                    throw IllegalArgumentException(
                        "Type mismatch for field $key: " +
                                "expected ${propertyType?.simpleName}, " +
                                "got ${value::class.simpleName}"
                    )
                }

                property.setter.call(transaction, value)
            } else {
                throw IllegalArgumentException("Field \"$key\" is not mutable")
            }
        }

        return repository.create(transaction)
    }
}
