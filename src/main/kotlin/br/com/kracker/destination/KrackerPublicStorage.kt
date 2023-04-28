package br.com.kracker.destination

import br.com.kracker.KrackerTrack
import java.io.File
import java.io.ObjectOutputStream

class KrackerPublicStorage<A, B>(private val parentDir: File) : KrackerTrack<A, B> {

    override fun track(initial: Pair<A, B>) {
        val task = {
            val file = File(parentDir, FILE_NAME)
            val oos = ObjectOutputStream(file.outputStream())
            oos.writeObject(initial.first to initial.second)
            oos.close()
        }

        task()
    }

    private companion object {
        const val FILE_NAME = "public.kracker"
    }
}
