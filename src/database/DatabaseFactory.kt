package database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.openpizza.database.MemoryTableCreation

object DatabaseFactory {
    private const val DRIVER_NAME = "org.h2.Driver"

    private const val IN_MEMORY_DATABASE = "jdbc:h2:mem:test"

    fun init() {
        Database.connect(hikari())
        MemoryTableCreation.create()
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = DRIVER_NAME
        config.jdbcUrl = IN_MEMORY_DATABASE
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(
        block: suspend () -> T
    ): T =
        newSuspendedTransaction { block() }
}