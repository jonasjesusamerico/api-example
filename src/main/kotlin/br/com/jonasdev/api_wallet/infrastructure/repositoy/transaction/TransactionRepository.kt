package br.com.jonasdev.api_wallet.infrastructure.repositoy.transaction

import br.com.jonasdev.api_wallet.domain.transaction.TransactionDomain

interface TransactionRepository {

    fun create(transaction : TransactionDomain)

}