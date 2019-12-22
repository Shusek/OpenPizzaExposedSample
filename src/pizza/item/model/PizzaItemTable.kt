package org.openpizza.pizza.item.model

import org.jetbrains.exposed.sql.Table
import org.openpizza.pizza.sizes.model.PizzaSizeTable
import org.openpizza.pizza.types.model.PizzaTypeTable

object PizzaItemTable : Table("pizza_item") {
    val id = integer("id").primaryKey().autoIncrement()
    val typeId = integer("type_id") references PizzaTypeTable.id
    val sizeId = integer("size_id") references PizzaSizeTable.id
}
