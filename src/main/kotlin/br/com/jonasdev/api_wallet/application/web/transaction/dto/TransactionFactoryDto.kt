package br.com.jonasdev.api_wallet.application.web.transaction.dto

import br.com.jonasdev.api_wallet.domain.transaction.TransactionDomain

fun TransactionResponseDto.Companion.fromDomain(domain: TransactionDomain)  = TransactionResponseDto(
    id = domain.id,
    purchaseDate = domain.purchaseDate,
    description = domain.description,
    sector = domain.sector,
    paymentMethod = domain.paymentMethod,
    amount = domain.amount,
    status = domain.status
)
