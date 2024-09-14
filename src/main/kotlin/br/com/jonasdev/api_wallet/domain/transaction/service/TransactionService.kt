package br.com.jonasdev.api_wallet.domain.transaction.service

import br.com.jonasdev.api_wallet.domain.transaction.TransactionDomain
import java.util.*

interface TransactionService {

    fun create(toDomain: TransactionDomain)

    fun findById(id: Long): Optional<TransactionDomain>

}