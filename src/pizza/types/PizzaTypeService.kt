package org.openpizza.pizza.types

import database.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.openpizza.pizza.types.model.NewPizzaType
import org.openpizza.pizza.types.model.PizzaTypeRecord
import org.openpizza.pizza.types.model.PizzaTypeTable

class PizzaTypeService {

    suspend fun getAllPizzaTypes(): List<PizzaTypeRecord> = dbQuery {
        PizzaTypeTable.selectAll().map { toPizzaTypeRecord(it) }
    }

    suspend fun getPizzaType(id: Int): PizzaTypeRecord? = dbQuery {
        PizzaTypeTable.select {
            (PizzaTypeTable.id eq id)
        }.mapNotNull { toPizzaTypeRecord(it) }
            .singleOrNull()
    }

    suspend fun updatePizzaType(id: Int, updatePizzaType: NewPizzaType): PizzaTypeRecord? {
        dbQuery {
            PizzaTypeTable.update({ PizzaTypeTable.id eq id }) {
                it[name] = updatePizzaType.name
                it[basePrize] = updatePizzaType.basePrize
            }
        }
        return getPizzaType(id)
    }

    suspend fun addPizzaType(widget: NewPizzaType): PizzaTypeRecord {
        var key = 0
        dbQuery {
            key = (PizzaTypeTable.insert {
                it[name] = widget.name
                it[basePrize] = widget.basePrize
            } get PizzaTypeTable.id)
        }
        return getPizzaType(key)!!
    }

    suspend fun deletePizzaType(id: Int): Boolean {
        return dbQuery {
            PizzaTypeTable.deleteWhere { PizzaTypeTable.id eq id } > 0
        }
    }

    private fun toPizzaTypeRecord(row: ResultRow): PizzaTypeRecord =
        PizzaTypeRecord(
            id = row[PizzaTypeTable.id],
            name = row[PizzaTypeTable.name],
            basePrize = row[PizzaTypeTable.basePrize]
        )
}