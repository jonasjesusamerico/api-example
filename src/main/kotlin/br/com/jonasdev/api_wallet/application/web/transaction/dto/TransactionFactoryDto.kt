package br.com.jonasdev.api_wallet.application.web.transaction.dto

import br.com.jonasdev.api_wallet.domain.model.transaction.TransactionDomain
import br.com.jonasdev.api_wallet.domain.representation.transaction.TransactionDomainRepresentation

fun TransactionResponse.fromDomain(domain: TransactionDomainRepresentation) = TransactionResponse(
    id = domain.id,
    purchaseDate = domain.purchaseDate,
    description = domain.description,
    sector = domain.sector,
    paymentMethod = domain.paymentMethod,
    amount = domain.amount,
    status = domain.status
)

fun TransactionResponse.toDomain() = TransactionDomain(
    id = this.id,
    purchaseDate = this.purchaseDate,
    description = this.description,
    sector = this.sector,
    paymentMethod = this.paymentMethod,
    amount = this.amount,
    status = this.status
)

fun TransactionRequest.toDomain() = TransactionDomain(
    purchaseDate = this.purchaseDate,
    description = this.description,
    sector = this.sector,
    paymentMethod = this.paymentMethod,
    amount = this.amount,
    status = this.status
)
