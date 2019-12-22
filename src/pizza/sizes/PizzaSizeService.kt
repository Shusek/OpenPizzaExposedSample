package org.openpizza.pizza.sizes

import database.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.openpizza.pizza.sizes.model.NewPizzaSize
import org.openpizza.pizza.sizes.model.PizzaSizeRecord
import org.openpizza.pizza.sizes.model.PizzaSizeTable

class PizzaSizeService {

    suspend fun getAllPizzaSizes(): List<PizzaSizeRecord> = dbQuery {
        PizzaSizeTable.selectAll().map { toPizzaSizeRecord(it) }
    }

    suspend fun getPizzaSize(id: Int): PizzaSizeRecord? = dbQuery {
        PizzaSizeTable.select {
            (PizzaSizeTable.id eq id)
        }.mapNotNull { toPizzaSizeRecord(it) }
            .singleOrNull()
    }

    suspend fun updatePizzaSize(id: Int, updatePizzaSize: NewPizzaSize): PizzaSizeRecord? {
        dbQuery {
            PizzaSizeTable.update({ PizzaSizeTable.id eq id }) {
                it[name] = updatePizzaSize.name
            }
        }
        return getPizzaSize(id)
    }

    suspend fun addPizzaSize(newPizzaSize: NewPizzaSize): PizzaSizeRecord {
        var key = 0
        dbQuery {
            key = (PizzaSizeTable.insert {
                it[name] = newPizzaSize.name
                it[additionalPrize] = newPizzaSize.additionalPrize
            } get PizzaSizeTable.id)
        }
        return getPizzaSize(key)!!
    }

    suspend fun deletePizzaSize(id: Int): Boolean {
        return dbQuery {
            PizzaSizeTable.deleteWhere { PizzaSizeTable.id eq id } > 0
        }
    }

    private fun toPizzaSizeRecord(row: ResultRow): PizzaSizeRecord =
        PizzaSizeRecord(
            id = row[PizzaSizeTable.id],
            name = row[PizzaSizeTable.name],
            additionalPrize = row[PizzaSizeTable.additionalPrize]
        )
}