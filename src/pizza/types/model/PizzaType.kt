package org.openpizza.pizza.types.model

data class PizzaTypeRecord(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val prize: Double
)

data class NewPizzaType(
    val name: String,
    val basePrize: Double
)
