package com.kracker.destination

import com.kracker.KrackerTrack
import java.io.File
import java.io.ObjectOutputStream
import java.io.Serializable

class KrackerPrivateStorage<A, B>(private val parentDir: File) : KrackerTrack<A, B> {

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
        const val FILE_NAME = "private.kracker"
    }
}
