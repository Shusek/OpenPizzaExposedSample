package org.openpizza.pizza.sizes.model

import org.jetbrains.exposed.sql.Table

object PizzaSizeTable : Table("pizza_size") {
    val id = integer("id").primaryKey().autoIncrement()
    val name = varchar("name", 55)
    val additionalPrize = double("additionalPrize")
}
