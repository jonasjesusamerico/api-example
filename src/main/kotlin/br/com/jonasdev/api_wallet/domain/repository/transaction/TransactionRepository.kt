package br.com.jonasdev.api_wallet.domain.repository.transaction

import br.com.jonasdev.api_wallet.domain.configuration.pageable.InternPageable
import br.com.jonasdev.api_wallet.domain.representation.transaction.TransactionDomainRepresentation
import br.com.jonasdev.api_wallet.library.pageable.InternPageableImpl
import java.util.*

interface TransactionRepository {

    fun create(transaction: TransactionDomainRepresentation)

    fun findById(id: Long): Optional<TransactionDomainRepresentation>

    fun findAll(pageable: InternPageable<TransactionDomainRepresentation>): InternPageableImpl<TransactionDomainRepresentation>

    fun update(id: Long, transaction: TransactionDomainRepresentation)

    fun delete(id: Long)

}