package behavioral

import org.junit.jupiter.api.Test

interface Command {
    fun execute()
}

class OrderAddCommand(val id: Long) : Command {
    override fun execute() {
        println("Adding order with id $id")
    }
}

class OrderPayCommand(val id: Long) : Command {
    override fun execute() {
        println("Paying for order with id $id")
    }
}

class CommandProcessor {
    private val queue = arrayListOf<Command>()

    fun addToQueue(command: Command): CommandProcessor = apply { queue.add(command) }

    fun processCommands(): CommandProcessor = apply {
        queue.forEach { it.execute() }
        queue.clear()
    }
}

class CommandTest {
    @Test
    fun `test command pattern`() {
        CommandProcessor()
                .addToQueue(OrderAddCommand(1))
                .addToQueue(OrderPayCommand(23))
                .processCommands()



    }
}