package org.openpizza.pizza.sizes.model

data class PizzaSizeRecord(
    val id: Int,
    val name: String,
    val additionalPrize: Double
)

data class NewPizzaSize(
    val name: String,
    val additionalPrize: Double
)
