import br.com.kracker.krack
import br.com.kracker.Kracking
import java.io.File

fun main() {
    Kracking.useConsoleLog()
    Kracking.usePrivateStorage(File("/private/sdcard/app/krack/logs"))
    Kracking.usePublicStorage(File("/public/sdcard/app/krack/logs"))

    krack<String, String, Unit> {
        track("" to "")
    }
}
