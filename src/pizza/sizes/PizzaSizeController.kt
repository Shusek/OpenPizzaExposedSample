package org.openpizza.pizza.sizes


import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import org.openpizza.pizza.sizes.model.NewPizzaSize

fun Route.pizzaSizeController() {
    val pizzaService = PizzaSizeService()

    route("/sizes") {
        guestPizzaSizeEndpoints(pizzaService)
        adminPizzaSizeEndpoints(pizzaService)
    }
}

//TODO this endpoints should be visible only for authenticated admin
private fun Route.adminPizzaSizeEndpoints(pizzaService: PizzaSizeService) {
    post("/") {
        val newPizza = call.receive<NewPizzaSize>()
        call.respond(HttpStatusCode.Created, pizzaService.addPizzaSize(newPizza))
    }

    put("/{id}") {
        val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
        val widget = call.receive<NewPizzaSize>()
        val updated = pizzaService.updatePizzaSize(id, widget)
        if (updated == null) call.respond(HttpStatusCode.NotFound)
        else call.respond(HttpStatusCode.OK, updated)
    }

    delete("/{id}") {
        val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
        pizzaService.deletePizzaSize(id)
        call.respond(HttpStatusCode.OK)
    }
}

private fun Route.guestPizzaSizeEndpoints(pizzaService: PizzaSizeService) {
    get("/") {
        call.respond(pizzaService.getAllPizzaSizes())
    }

    get("/{id}") {
        val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
        val pizza = pizzaService.getPizzaSize(id)
        if (pizza == null) call.respond(HttpStatusCode.NotFound)
        else call.respond(pizza)
    }
}