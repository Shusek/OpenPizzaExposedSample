package org.openpizza.pizza.tops.model

import org.jetbrains.exposed.sql.Table

object TopsTable : Table("") {
    val id = integer("id").primaryKey().autoIncrement()
    val name = varchar("name", 55)
    val description = varchar("description", 255)
    val prize = double("prize")
}

