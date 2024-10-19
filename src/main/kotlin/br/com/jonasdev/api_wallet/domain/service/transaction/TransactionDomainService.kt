package br.com.jonasdev.api_wallet.domain.service.transaction

import br.com.jonasdev.api_wallet.domain.configuration.pageable.InternPageable
import br.com.jonasdev.api_wallet.domain.model.transaction.TransactionDomain
import br.com.jonasdev.api_wallet.domain.repository.transaction.TransactionRepository
import br.com.jonasdev.api_wallet.domain.representation.transaction.TransactionDomainRepresentation
import br.com.jonasdev.api_wallet.library.pageable.InternPageableImpl
import jakarta.persistence.EntityNotFoundException
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

class TransactionDomainService(private val repository: TransactionRepository) : TransactionService {

    override fun create(toDomain: TransactionDomainRepresentation) {
        repository.create(toDomain)
    }

    override fun findById(id: Long): Optional<TransactionDomainRepresentation> {
        return repository.findById(id)
    }

    override fun findAll(pageable: InternPageable<TransactionDomainRepresentation>): InternPageableImpl<TransactionDomainRepresentation> {
        return repository.findAll(pageable)
    }

    override fun update(id: Long, transaction: TransactionDomainRepresentation) {
        return repository.update(id, transaction)
    }

    override fun delete(id: Long) {
        return repository.delete(id)
    }

    override fun updateFields(id: Long, fieldValue: Map<String, Any>) {
        val transaction = repository.findById(id)
            .orElseThrow { EntityNotFoundException("Transaction with id $id not found.") }

        fieldValue.forEach { (key, value) ->
            val property = TransactionDomain::class.memberProperties
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
