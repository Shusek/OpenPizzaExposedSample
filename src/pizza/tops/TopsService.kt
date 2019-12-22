package org.openpizza.pizza.tops

import database.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.openpizza.pizza.tops.model.NewTops
import org.openpizza.pizza.tops.model.TopsRecord
import org.openpizza.pizza.tops.model.TopsTable

class TopsService {

    suspend fun getAllTops(): List<TopsRecord> = dbQuery {
        TopsTable.selectAll().map { toTopsRecord(it) }
    }

    suspend fun getPizzaTops(id: Int): TopsRecord? = dbQuery {
        TopsTable.select {
            (TopsTable.id eq id)
        }.mapNotNull { toTopsRecord(it) }
            .singleOrNull()
    }

    suspend fun updatePizzaTops(id: Int, updateTops: NewTops): TopsRecord? {
        dbQuery {
            TopsTable.update({ TopsTable.id eq id }) {
                it[name] = updateTops.name
                it[description] = updateTops.description
                it[prize] = updateTops.prize
            }
        }
        return getPizzaTops(id)
    }

    suspend fun addPizzaTops(newTops: NewTops): TopsRecord {
        var key = 0
        dbQuery {
            key = (TopsTable.insert {
                it[name] = newTops.name
                it[description] = newTops.description
                it[prize] = newTops.prize
            } get TopsTable.id)
        }
        return getPizzaTops(key)!!
    }

    suspend fun deletePizzaTops(id: Int): Boolean {
        return dbQuery {
            TopsTable.deleteWhere { TopsTable.id eq id } > 0
        }
    }

    private fun toTopsRecord(row: ResultRow): TopsRecord =
        TopsRecord(
            id = row[TopsTable.id],
            name = row[TopsTable.name],
            description = row[TopsTable.description],
            prize = row[TopsTable.prize]
        )
}