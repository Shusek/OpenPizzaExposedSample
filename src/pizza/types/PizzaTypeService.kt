package org.openpizza.pizza.types

import database.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.openpizza.pizza.item.model.PizzaItemTable
import org.openpizza.pizza.types.model.NewPizzaType
import org.openpizza.pizza.types.model.PizzaTypeRecord
import org.openpizza.pizza.types.model.PizzaTypeTable

class PizzaTypeService {

    suspend fun getAllPizzaTypes(): List<PizzaTypeRecord> = dbQuery {
        PizzaItemTable.selectAll().map { toPizzaTypeRecord(it) }
    }

    suspend fun getPizzaType(id: Int): PizzaTypeRecord? = dbQuery {
        PizzaItemTable.select {
            (PizzaTypeTable.id eq id)
        }.mapNotNull { toPizzaTypeRecord(it) }
            .singleOrNull()
    }

    suspend fun updatePizzaType(id: Int, updatePizzaType: NewPizzaType): PizzaTypeRecord? {
        dbQuery {
            PizzaItemTable.update({ PizzaItemTable.id eq id }) {

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
            id = row[PizzaItemTable.id],
            image = row[PizzaItemTable.image],
            description = row[PizzaItemTable.description],
            title = row[PizzaItemTable.title],
            prize = row[PizzaItemTable.prize]
        )
}