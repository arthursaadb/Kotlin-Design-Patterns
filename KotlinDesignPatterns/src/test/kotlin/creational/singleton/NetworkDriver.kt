package creational.singleton

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

object NetworkDriver {
    init {
        println("Initialization: $this")
    }

    fun log(): NetworkDriver = apply { println("Initialization: $this") }
}

class NetworkDriverTest {

    @Test
    fun `test singleton network driver`() {
        val networkDriver1 = NetworkDriver.log()
        val networkDriver2 = NetworkDriver.log()

        Assertions.assertThat(networkDriver1).isSameAs(networkDriver2)
    }
}