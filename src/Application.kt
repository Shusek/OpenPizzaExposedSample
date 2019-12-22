package org.openpizza

import com.fasterxml.jackson.databind.SerializationFeature
import database.DatabaseFactory
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.jackson.jackson
import io.ktor.routing.routing
import order.model.ordersController
import org.openpizza.pizza.sizes.pizzaSizeController
import org.openpizza.pizza.tops.topsController
import org.openpizza.pizza.types.pizzaTypesController

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        jackson {
            configure(SerializationFeature.INDENT_OUTPUT, true)
        }
    }
    DatabaseFactory.init()

    routing {
        pizzaTypesController()
        pizzaSizeController()
        topsController()
        ordersController()
    }
}

