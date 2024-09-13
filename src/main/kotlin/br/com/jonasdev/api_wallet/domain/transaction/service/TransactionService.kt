package br.com.jonasdev.api_wallet.domain.transaction.service

import br.com.jonasdev.api_wallet.domain.transaction.TransactionDomain

interface TransactionService {

    fun create(toDomain: TransactionDomain)

}