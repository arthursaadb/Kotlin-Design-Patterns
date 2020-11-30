package creational.builder

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Component private constructor(builder: Builder) {
    var param1: String? = null
    var param2: Int? = null
    var param3: Boolean? = null

    init {
        this.param1 = builder.getParam1()
        this.param2 = builder.getParam2()
        this.param3 = builder.getParam3()
    }

    class Builder {
        private var param1: String? = null
        private var param2: Int? = null
        private var param3: Boolean? = null

        fun setParam1(param1: String) = apply { this.param1 = param1 }
        fun getParam1() = this.param1

        fun setParam2(param2: Int) = apply { this.param2 = param2 }
        fun getParam2() = this.param2

        fun setParam3(param3: Boolean) = apply { this.param3 = param3 }
        fun getParam3() = this.param3

        fun build() = Component(this)
    }
}

class ComponentTest {
    @Test
    fun `test builder class`() {
        val component = Component.Builder()
            .setParam1("value")
            .setParam2(2)
            .build()

        Assertions.assertThat(component.param1).isEqualTo("value")
        Assertions.assertThat(component.param2).isEqualTo(2)
        Assertions.assertThat(component.param3).isNull()
    }
}