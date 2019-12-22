package org.openpizza.pizza.types.model

data class PizzaTypeRecord(
    val id: Int,
    val name: String,
    val basePrize: Double
)

data class NewPizzaType(
    val name: String,
    val basePrize: Double
)
