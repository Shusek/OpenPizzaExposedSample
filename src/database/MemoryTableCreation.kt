package org.openpizza.database

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.openpizza.order.model.model.OrderTable
import org.openpizza.pizza.item.model.PizzaItemTable
import org.openpizza.pizza.sizes.model.PizzaSizeTable
import org.openpizza.pizza.tops.model.TopsTable
import org.openpizza.pizza.types.model.PizzaTypeTable

object MemoryTableCreation {

    fun create() {
        transaction {
            SchemaUtils.create(PizzaSizeTable)
            SchemaUtils.create(PizzaTypeTable)
            SchemaUtils.create(TopsTable)
            SchemaUtils.create(OrderTable)
            SchemaUtils.create(PizzaItemTable)
            createSamplePizzaItem()
        }
    }

    private fun createSamplePizzaItem() {
        PizzaItemTable.insert {
            it[title] = "Margarita"
            it[description] =
                "Otóż według najbardziej znanej legendy królowa włoskich pizz zawdzięcza swoje imię królowej Marghericie.\n" +
                        "\n" +
                        "Podobno było tak: W 1889 roku piękna i kochana przez lud królowa Włoch, Margherita di Savoia (po naszemu: Małgorzata Sabaudzka) i jej mąż król Umberto I spędzali wakacje w Neapolu. Królowa koniecznie chciała spróbować pizzy, wszakże miasto słynęło z tego dania w całym kraju.\n" +
                        "\n" +
                        "Przygotowanie potrawy dla powierzono najsłynniejszemu piekarzowi w okolicy: Raffaele Esposito, z najlepszej neapolitańskiej pizzerii: Brandi (założonej w 1780 roku!).\n" +
                        "\n" +
                        "Raffaele przygotował dla królowej pizzę, której podstawowymi składnikami były pomidory, ser mozzarella i bazylia. Składniki miały symbolizować barwy flagi włoskiej: zieleń, biel i czerwień. Na cześć królowej nazwał danie jej imieniem: Margherita."
            it[prize] = 33.00
            it[image] = "image1.jpg"
        }

        PizzaItemTable.insert {
            it[title] = "Salami"
            it[description] =
                "Otóż według najbardziej znanej legendy królowa włoskich pizz zawdzięcza swoje imię królowej Marghericie.\n" +
                        "\n" +
                        "Podobno było tak: W 1889 roku piękna i kochana przez lud królowa Włoch, Margherita di Savoia (po naszemu: Małgorzata Sabaudzka) i jej mąż król Umberto I spędzali wakacje w Neapolu. Królowa koniecznie chciała spróbować pizzy, wszakże miasto słynęło z tego dania w całym kraju.\n" +
                        "\n" +
                        "Przygotowanie potrawy dla powierzono najsłynniejszemu piekarzowi w okolicy: Raffaele Esposito, z najlepszej neapolitańskiej pizzerii: Brandi (założonej w 1780 roku!).\n" +
                        "\n" +
                        "Raffaele przygotował dla królowej pizzę, której podstawowymi składnikami były pomidory, ser mozzarella i bazylia. Składniki miały symbolizować barwy flagi włoskiej: zieleń, biel i czerwień. Na cześć królowej nazwał danie jej imieniem: Margherita."
            it[prize] = 22.00
            it[image] = "image2.jpg"
        }


        PizzaItemTable.insert {
            it[title] = "Peperoni"
            it[description] =
                "Obok pizzy Margherita to kolejna klasyczna pizza. Pizza z salami jest uwielbiana szczególnie przez dzieci, które z reguły nie lubią, gdy jest na niej dużo dodatków."
            it[prize] = 33.00
            it[image] = "image1.jpg"
        }
    }
}