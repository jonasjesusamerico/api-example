package br.com.jonasdev.api_wallet.domain.repository.transaction

import br.com.jonasdev.api_wallet.domain.representation.transaction.TransactionDomainRepresentation
import java.util.*

interface TransactionRepository {

    fun create(transaction: TransactionDomainRepresentation)

    fun findById(id: Long): Optional<TransactionDomainRepresentation>

    fun findAll(): List<TransactionDomainRepresentation>

    fun update(id: Long, transaction: TransactionDomainRepresentation)

    fun delete(id: Long)

}