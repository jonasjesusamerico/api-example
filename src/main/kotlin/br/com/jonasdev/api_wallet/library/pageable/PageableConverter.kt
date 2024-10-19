package br.com.jonasdev.api_wallet.library.pageable

import br.com.jonasdev.api_wallet.domain.configuration.pageable.InternPageable
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

object PageableConverter {
    fun <T> convert(internPageable: InternPageable<T>): PageRequest {
        return PageRequest.of(
            internPageable.pageNumber(),
            internPageable.pageSize(),
            parseSortString(internPageable.sort())
        )
    }

    private fun parseSortString(sort: String?): Sort {
        if (sort.isNullOrEmpty()) {
            return Sort.unsorted()
        }
        val parts = sort.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val orders: MutableList<Sort.Order> = ArrayList()

        for (part in parts) {
            val pair = part.trim { it <= ' ' }.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val property = pair[0]
            var direction = Sort.Direction.ASC

            if (pair.size > 1 && "desc".equals(pair[1], ignoreCase = true)) {
                direction = Sort.Direction.DESC
            }

            orders.add(Sort.Order(direction, property))
        }

        return Sort.by(orders)
    }
}