package br.com.jonasdev.api_example.infrastructure.config.datasource

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.support.TransactionSynchronizationManager

@Profile("!test")
@Configuration
@EnableJpaRepositories(
    basePackages = ["br.com.jonasdev.api_example.infrastructure.persistence"],
    entityManagerFactoryRef = "entityManager",
    transactionManagerRef = "transactionManager"
)
@EnableTransactionManagement
@EntityScan(basePackages = ["br.com.jonasdev.api_example.infrastructure.entities"])
class TransactionRoutingDataSource(
    dataBaseProperties: DataBaseProperties,
    hikariPrimaryProperties: HikariPrimaryProperties,
    hikariReplicaProperties: HikariReplicaProperties
): AbstractRoutingDataSource() {

    @Value("\${spring.jpa.show-sql}")
    private var showSql: Boolean = false

    @Value("\${spring.jpa.hibernate.ddl-auto}")
    private var ddlAuto: String = "none"

    @Value("\${spring.jpa.properties.hibernate.format_sql}")
    private val formatSql: Boolean = false

    init {
        val dataSourceMap = HashMap<Any, Any>()
        dataSourceMap[DataSourceType.READ_WRITE] = HikariDataSource(hikariPrimaryProperties.buildConfig(dataBaseProperties))
        dataSourceMap[DataSourceType.READ_ONLY] = HikariDataSource(hikariReplicaProperties.buildConfig(dataBaseProperties))
        this.setTargetDataSources(dataSourceMap)
    }

    override fun determineCurrentLookupKey(): DataSourceType {
        if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            return DataSourceType.READ_ONLY
        }

        return DataSourceType.READ_WRITE
    }

    @Bean
    @Primary
    fun entityManager(): LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource()
        em.setPackagesToScan("br.com.jonasdev.api_example.infrastructure.entities")

        val vendorAdapter = HibernateJpaVendorAdapter()
        em.jpaVendorAdapter = vendorAdapter
        val jpaDialect = vendorAdapter.jpaDialect
        jpaDialect.setPrepareConnection(false)



        val properties = HashMap<String, Any>()
        properties["hibernate.hbm2ddl.auto"] = ddlAuto
        properties["hibernate.dialect"] = "org.hibernate.dialect.PostgreSQLDialect"
        properties["hibernate.show_sql"] = showSql
        properties["hibernate.format_sql"] = formatSql
        em.setJpaPropertyMap(properties)

        return em
    }

    @Bean
    @Primary
    fun dataSource() = LazyConnectionDataSourceProxy(this)
}