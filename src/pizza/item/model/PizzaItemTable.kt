package org.openpizza.pizza.item.model

import org.jetbrains.exposed.sql.Table

object PizzaItemTable : Table("pizza_item") {
    val id = integer("id").primaryKey().autoIncrement()
    val title = text("title")
    val description = text("description")
    val image = text("image")
    val prize = double("prize")
}
