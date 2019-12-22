package org.openpizza.order.model.model

import org.jetbrains.exposed.sql.Table

object OrderTable : Table("order") {
    val id = integer("id").primaryKey().autoIncrement()
    val name = varchar("userName", 50)
    val surName = varchar("surName", 50)
    val userPhone = varchar("userPhone", 50)
    val userAddress = varchar("userAddress", 50)
    val userAddressFlat = varchar("userAddressFlat", 25)
    val finalPrize = double("finalPrize")
    val orderTime = long("orderTime")
    val expectedDeliveryTime = long("expectedDeliveryTime")
}
