import com.kracker.krack
import com.kracker.Kracking
import java.io.File

fun main() {
    Kracking.useConsoleLog()
    Kracking.usePrivateStorage(File("/private/sdcard/app/krack/logs"))
    Kracking.usePublicStorage(File("/public/sdcard/app/krack/logs"))

    krack<String, String, Unit> {
        it.track("" to "")
    }
}
