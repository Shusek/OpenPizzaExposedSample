package org.openpizza.order.model.model

data class OrderRecord(
    val id: Int,
    val name: String,
    val surName: String,
    val userPhone: String,
    val userAddress: String,
    val userAddressFlat: String,
    val finalPrize: Double,
    val orderTime: Long,
    val expectedDeliveryTime: Long
)

data class NewPizzaOrder(
    val name: String,
    val surName: String,
    val userPhone: String,
    val userAddress: String,
    val userAddressFlat: String
)
