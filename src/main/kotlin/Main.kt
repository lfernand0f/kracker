import br.com.kracker.krack
import br.com.kracker.Kracking
import java.io.File

fun main() {
//    Kracking.useConsoleLog()
//    Kracking.usePrivateStorage(File("/private/sdcard/app/krack/logs"))
//    Kracking.usePublicStorage(File("/public/sdcard/app/krack/logs"))
    Kracking.useCustomStorage {
        println(it.toString())
    }

    krack<String, String, Unit> {
        track("A" to "B")
        dispatch()
    }
}

private fun doSomething(): String = krack<String, String, String> {
    println("Hello, world.")
    return "Olá, mundo."
}

class MyClass {
    fun doSomething() {
        // faz alguma coisa...
    }
}
