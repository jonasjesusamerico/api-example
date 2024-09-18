package br.com.jonasdev.api_wallet.domain.service.transaction

import br.com.jonasdev.api_wallet.domain.representation.transaction.TransactionDomainRepresentation
import java.util.*

interface TransactionService {

    fun create(toDomain: TransactionDomainRepresentation)

    fun findById(id: Long): Optional<TransactionDomainRepresentation>

    fun findAll(): List<TransactionDomainRepresentation>

    fun update(id: Long, transaction: TransactionDomainRepresentation)

    fun delete(id: Long)

    fun updateFields(id: Long, fieldValue: Map<String, Any>)

}