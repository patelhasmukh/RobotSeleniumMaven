package io.automation

import com.google.common.truth.Truth.assertThat
import io.automation.base.BaseTest
import io.automation.enums.AIRLINE
import io.automation.enums.ARRRIVAL
import io.automation.enums.DEPARTURE
import io.automation.enums.FLIGHT
import io.automation.pages.onMakeMytripHomePage
import org.junit.jupiter.api.Test

class MakeMytripTest : BaseTest() {

    @Test
    fun `I want to do search flights based on valid criteria`() {
        onMakeMytripHomePage {
            selectFromLocationFromList("BOM", "DEL")
        } searchFlights {
            filterFlightsOn(FLIGHT.NONSTOP.flightTiming,
                    DEPARTURE.NIGHT.departureTime,
                    ARRRIVAL.NIGHT.arrivalTime,
                    AIRLINE.INDIGO.airlineName)
            assertThat(getAllFlights()?.size).isEqualTo(4)
        }
    }

    @Test
    fun `I should not get flights for invalid search criteria`() {
        onMakeMytripHomePage {
            selectFromLocationFromList("BOM", "DEL")
        } searchFlights {
            filterFlightsOn(FLIGHT.ONESTOP.flightTiming,
                    DEPARTURE.MORNING.departureTime,
                    ARRRIVAL.MORNING.arrivalTime,
                    AIRLINE.INDIGO.airlineName)
            assertThat(getAllFlights()?.size).isEqualTo(0)
        }
    }

}