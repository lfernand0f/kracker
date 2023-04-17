import com.kracker.krack
import com.kracker.Kracking
import java.io.File

fun main() {
    /*krack(
        Kracking.usePublicStorage(File("/public/sdcard/app/krack/logs/")),
        Kracking.usePrivateStorage(File("/private/sdcard/app/krack/logs/")), onKrack = {
        it.track(Sample.SAMPLE_ONE to Sample.SAMPLE_ONE.key)
    })*/


   /* krack(Kracking.useConsoleLog()) {
        it.track(Sample.SAMPLE_ONE to Sample.SAMPLE_ONE.key)
        it.dispatch()
    }

    krack {
        it.track(Sample.SAMPLE_TWO to Sample.SAMPLE_TWO.key)
        it.dispatch()
    }

    doSomethingAsync()*/
}

enum class Sample(val key: String) {
    SAMPLE_ONE("ONE"),
    SAMPLE_TWO("TWO");
}

suspend fun doSomethingAsync(): Int = 0