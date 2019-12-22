package org.openpizza.database

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.openpizza.order.model.model.OrderTable
import org.openpizza.pizza.item.model.PizzaItemTable
import org.openpizza.pizza.sizes.model.PizzaSizeTable
import org.openpizza.pizza.tops.model.TopsTable
import org.openpizza.pizza.types.model.PizzaTypeTable

object MemoryTableCreation {

    fun create() {
        transaction {
            SchemaUtils.create(PizzaSizeTable)
            SchemaUtils.create(PizzaTypeTable)
            SchemaUtils.create(TopsTable)
            SchemaUtils.create(OrderTable)
            SchemaUtils.create(PizzaItemTable)
            createSamplePizzaSizes()
            createSamplePizzaTypes()
            createSamplePizzaTops()
        }
    }

    private fun createSamplePizzaSizes() {
        PizzaSizeTable.insert {
            it[name] = "Normal"
            it[additionalPrize] = 0.00
        }

        PizzaSizeTable.insert {
            it[name] = "XL"
            it[additionalPrize] = 15.00
        }
    }

    private fun createSamplePizzaTypes() {
        PizzaTypeTable.insert {
            it[name] = "Normal"
            it[basePrize] = 22.00
        }

        PizzaTypeTable.insert {
            it[name] = "Margarita"
            it[basePrize] = 35.00
        }
    }

    private fun createSamplePizzaTops() {
        PizzaSizeTable.insert {
            it[name] = "Normal"
            it[additionalPrize] = 0.00
        }

        PizzaSizeTable.insert {
            it[name] = "XL"
            it[additionalPrize] = 15.00
        }
    }
}