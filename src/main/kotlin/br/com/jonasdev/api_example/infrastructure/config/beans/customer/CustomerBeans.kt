package br.com.jonasdev.api_example.infrastructure.config.beans.customer

import br.com.jonasdev.api_example.domain.repository.customer.CustomerRepository
import br.com.jonasdev.api_example.domain.service.customer.CustomerDomainService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CustomerBeans {

    @Bean
    fun customerService(repository: CustomerRepository) = CustomerDomainService(repository)

}
