package org.openpizza.pizza.tops.model


data class TopsRecord(
    val id: Int,
    val name: String,
    val description: String,
    val prize: Double
)

data class NewTops(
    val name: String,
    val description: String,
    val prize: Double
)
