package br.com.jonasdev.api_wallet.domain.model.transaction

import br.com.jonasdev.api_wallet.domain.representation.transaction.TransactionDomainRepresentation
import java.math.BigDecimal
import java.time.LocalDate

data class TransactionDomain(
    override val id: Long? = null,
    override var purchaseDate: LocalDate,
    override var description: String,
    override var sector: String,
    override var paymentMethod: String,
    override var amount: BigDecimal,
    override var status: Boolean
) : TransactionDomainRepresentation