package org.openpizza.pizza.types


import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import org.openpizza.pizza.types.model.NewPizzaType

fun Route.pizzaTypesController() {
    val pizzaTypeService = PizzaTypeService()

    route("/pizza") {
        guestPizzaEndpoints(pizzaTypeService)
        adminPizzaTypeEndpoints(pizzaTypeService)
    }
}

//TODO this endpoints should be visible only for authenticated admin
private fun Route.adminPizzaTypeEndpoints(pizzaService: PizzaTypeService) {
    post("/") {
        val newPizza = call.receive<NewPizzaType>()
        call.respond(HttpStatusCode.Created, pizzaService.addPizzaType(newPizza))
    }

    put("/{id}") {
        val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
        val widget = call.receive<NewPizzaType>()
        val updated = pizzaService.updatePizzaType(id, widget)
        if (updated == null) call.respond(HttpStatusCode.NotFound)
        else call.respond(HttpStatusCode.OK, updated)
    }

    delete("/{id}") {
        val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
        pizzaService.deletePizzaType(id)
        call.respond(HttpStatusCode.OK)
    }
}

private fun Route.guestPizzaEndpoints(pizzaService: PizzaTypeService) {
    get("/") {
        call.respond(pizzaService.getAllPizzaTypes())
    }

    get("/{id}") {
        val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
        val pizza = pizzaService.getPizzaType(id)
        if (pizza == null) call.respond(HttpStatusCode.NotFound)
        else call.respond(pizza)
    }
}