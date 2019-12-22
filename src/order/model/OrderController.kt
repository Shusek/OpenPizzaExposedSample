package order.model


import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import org.openpizza.order.model.model.NewPizzaOrder

fun Route.ordersController() {
    val orderService = OrderService()

    route("/orders") {
        guestOrdersEndpoints(orderService)
        adminOrdersEndpoints(orderService)
    }
}

//TODO this endpoints should be visible only for authenticated admin
private fun Route.adminOrdersEndpoints(orderService: OrderService) {

    get("/") {
        call.respond(orderService.getAllOrders())
    }

    get("/{id}") {
        val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
        val pizza = orderService.getOrder(id)
        if (pizza == null) call.respond(HttpStatusCode.NotFound)
        else call.respond(pizza)
    }

    put("/{id}") {
        val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
        val widget = call.receive<NewPizzaOrder>()
        val updated = orderService.updateOrder(id, widget)
        if (updated == null) call.respond(HttpStatusCode.NotFound)
        else call.respond(HttpStatusCode.OK, updated)
    }
}

private fun Route.guestOrdersEndpoints(orderService: OrderService) {
    post("/") {
        val newPizza = call.receive<NewPizzaOrder>()
        call.respond(HttpStatusCode.Created, orderService.addOrder(newPizza))
    }
}