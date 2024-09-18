package br.com.jonasdev.api_wallet.application.web.transaction.dto

import java.math.BigDecimal
import java.time.LocalDate

data class TransactionResponseDto(
    var id: Long? = null,
    var purchaseDate: LocalDate = LocalDate.now(),
    var description: String = "",
    var sector: String = "",
    var paymentMethod: String = "",
    var amount: BigDecimal = BigDecimal.ZERO,
    var status: Boolean = false
)