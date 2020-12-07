package behavioral

import org.junit.jupiter.api.Test
import java.io.File

interface EventListener {
    fun update(eventType: String?, file: File?)
}

class EventManager(vararg operations: String) {
    var listeners = hashMapOf<String, ArrayList<EventListener>>()

    init {
        for (operation in operations) {
            listeners[operation] = ArrayList()
        }
    }

    fun subscribe(eventType: String?, listener: EventListener) {
        val users = listeners[eventType]
        users?.add(listener)
    }

    fun unsubscribe(eventType: String?, listener: EventListener) {
        val users = listeners[eventType]
        users?.remove(listener)
    }

    fun notify(eventType: String?, file: File?) {
        val users = listeners[eventType]

        users?.let {
            for (listener in it) {
                listener.update(eventType, file)
            }
        }
    }
}

class Editor {
    var events: EventManager = EventManager("open", "save")
    private var file: File? = null

    fun openFile(filePath: String) {
        file = File(filePath)
        events.notify("open", file)
    }

    fun saveFile() {
        file?.let {
            events.notify("save", it)
        }
    }
}

class Email : EventListener {
    override fun update(eventType: String?, file: File?) {
        println("Email event")
    }
}

class Logger : EventListener {
    override fun update(eventType: String?, file: File?) {
        println("Logger event")
    }
}

class ObserverTest {

    @Test
    fun `test observer`() {
        val editor = Editor()
        editor.events.subscribe("open", Logger())
        editor.events.subscribe("save", Email())
    }
}