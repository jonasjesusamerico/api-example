package br.com.jonasdev.api_wallet.infrastructure.repositoy.transaction

import br.com.jonasdev.api_wallet.domain.transaction.TransactionDomain
import java.util.*

interface TransactionRepository {

    fun create(transaction: TransactionDomain)

    fun findById(id: Long): Optional<TransactionDomain>

}