package creational.lazyinitialization

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class AlertBox {
    var message: String? = null

    fun show() {
        println("Alertbox $this: $message")
    }
}

class Window {
    val box by lazy { AlertBox() }

    fun showMessage(message: String) {
        box.message = message
        box.show()
    }
}

class Window2 {
    lateinit var box: AlertBox

    fun showMessage(message: String) {
        box = AlertBox()
        box.message = message
        box.show()
    }
}

class WindowTest {
    @Test
    fun `test alert box message`() {
        val window = Window()
        window.showMessage("Hello window")

        Assertions.assertThat(window.box).isNotNull
    }

    @Test
    fun `test window 2 message`() {
        val window2 = Window2()
        window2.showMessage("test")

        Assertions.assertThat(window2.box).isNotNull
    }
}

