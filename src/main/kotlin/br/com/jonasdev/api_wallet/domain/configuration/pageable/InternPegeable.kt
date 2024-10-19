package br.com.jonasdev.api_wallet.domain.configuration.pageable

interface InternPageable<T> {

    fun content(): List<T>?

    fun pageNumber(): Int

    fun pageSize(): Int

    fun totalElements(): Long

    fun totalPages(): Int

    fun hasNext(): Boolean

    fun hasPrevious(): Boolean

    fun sort(): String?

}
