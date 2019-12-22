package order.model

import database.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.openpizza.order.model.model.NewPizzaOrder
import org.openpizza.order.model.model.OrderRecord
import org.openpizza.order.model.model.OrderTable
import org.openpizza.order.model.model.OrderTable.expectedDeliveryTime
import org.openpizza.order.model.model.OrderTable.finalPrize
import org.openpizza.order.model.model.OrderTable.orderTime
import org.openpizza.order.model.model.OrderTable.surName
import org.openpizza.order.model.model.OrderTable.userAddress
import org.openpizza.order.model.model.OrderTable.userAddressFlat
import org.openpizza.order.model.model.OrderTable.userPhone
import java.time.Instant

class OrderService {

    suspend fun getAllOrders(): List<OrderRecord> = dbQuery {
        OrderTable.selectAll().map { toOrderRecord(it) }
    }

    suspend fun getOrder(id: Int): OrderRecord? = dbQuery {
        OrderTable.select {
            (OrderTable.id eq id)
        }.mapNotNull { toOrderRecord(it) }
            .singleOrNull()
    }

    suspend fun updateOrder(id: Int, updatePizzaType: NewPizzaOrder): OrderRecord? {
        dbQuery {
            OrderTable.update({ OrderTable.id eq id }) {
                it[name] = updatePizzaType.name
            }
        }
        return getOrder(id)
    }

    suspend fun addOrder(newOrder: NewPizzaOrder): OrderRecord {
        var key = 0
        dbQuery {
            key = (OrderTable.insert {
                it[name] = newOrder.name
                it[surName] = newOrder.surName
                it[userPhone] = newOrder.userPhone
                it[userAddress] = newOrder.userAddress
                it[userAddressFlat] = newOrder.userAddressFlat
                it[finalPrize] = 100.00
                it[orderTime] = Instant.now().epochSecond
                it[expectedDeliveryTime] = Instant.now().plusSeconds(3600).epochSecond
            } get OrderTable.id)
        }
        return getOrder(key)!!
    }

    private fun toOrderRecord(row: ResultRow): OrderRecord =
        OrderRecord(
            id = row[OrderTable.id],
            name = row[OrderTable.name],
            surName = row[surName],
            userPhone = row[userPhone],
            userAddress = row[userAddress],
            userAddressFlat = row[userAddressFlat],
            finalPrize = row[finalPrize],
            orderTime = row[orderTime],
            expectedDeliveryTime = row[expectedDeliveryTime]
        )
}