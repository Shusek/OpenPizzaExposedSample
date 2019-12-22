package org.openpizza.pizza.types.model

import org.jetbrains.exposed.sql.Table

object PizzaTypeTable : Table("pizza_type") {
    val id = integer("id").primaryKey().autoIncrement()
    val name = varchar("name", 55)
    val basePrize = double("basePrize")
}
