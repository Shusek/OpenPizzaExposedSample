package org.openpizza.pizza.tops


import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import org.openpizza.pizza.tops.model.NewTops

fun Route.topsController() {
    val pizzaTopsService = TopsService()

    route("/tops") {
        guestPizzaEndpoints(pizzaTopsService)
        adminPizzaTypeEndpoints(pizzaTopsService)
    }
}

//TODO this endpoints should be visible only for authenticated admin
private fun Route.adminPizzaTypeEndpoints(pizzaService: TopsService) {
    post("/") {
        val newTops = call.receive<NewTops>()
        call.respond(HttpStatusCode.Created, pizzaService.addPizzaTops(newTops))
    }

    put("/{id}") {
        val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
        val tops = call.receive<NewTops>()
        val updated = pizzaService.updatePizzaTops(id, tops)
        if (updated == null) call.respond(HttpStatusCode.NotFound)
        else call.respond(HttpStatusCode.OK, updated)
    }

    delete("/{id}") {
        val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
        pizzaService.deletePizzaTops(id)
        call.respond(HttpStatusCode.OK)
    }
}

private fun Route.guestPizzaEndpoints(pizzaService: TopsService) {
    get("/") {
        call.respond(pizzaService.getAllTops())
    }

    get("/{id}") {
        val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
        val pizza = pizzaService.getPizzaTops(id)
        if (pizza == null) call.respond(HttpStatusCode.NotFound)
        else call.respond(pizza)
    }
}
