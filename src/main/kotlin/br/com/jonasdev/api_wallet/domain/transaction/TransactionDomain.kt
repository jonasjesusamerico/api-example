package br.com.jonasdev.api_wallet.domain.transaction

import java.math.BigDecimal
import java.time.LocalDate

data class TransactionDomain(
    val id: Long? = null,
    val purchaseDate: LocalDate,
    val description: String,
    val sector: String,
    val paymentMethod: String,
    val amount: BigDecimal,
    var status: Boolean
)