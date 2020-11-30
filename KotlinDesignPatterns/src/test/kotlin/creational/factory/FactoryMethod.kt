package creational.factory

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

sealed class Country {
    object Canada : Country()
    object Spain : Country()
    class Greece(val someProperty: String) : Country()
}

class Currency(val code: String)

object CurrencyFactory {
    fun currencyForCountry(country: Country): Currency {
        return when (country) {
            is Country.Canada -> Currency("CAD")
            is Country.Spain -> Currency("EUR")
            is Country.Greece -> Currency("EUR")
        }
    }
}

class FactoryMethodTest {
    @Test
    fun `test country currency`() {
        val canadianCurrency = CurrencyFactory.currencyForCountry(Country.Canada).code
        val spainCurrency = CurrencyFactory.currencyForCountry(Country.Spain).code

        Assertions.assertThat(canadianCurrency).isEqualTo("CAD")
        Assertions.assertThat(spainCurrency).isEqualTo("EUR")
    }
}