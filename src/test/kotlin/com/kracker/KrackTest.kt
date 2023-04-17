package com.kracker

import com.kracker.destination.KrackerCustom
import com.kracker.destination.KrackerNoop
import com.kracker.destination.KrackerPrivateStorage
import com.kracker.destination.KrackerPublicStorage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.File

class KrackTest {

    @Test
    fun `krack code block specifying custom destination`() {
        val krackResultCodeBlock = krack(Kracking.chooseDestination {
            println("Some place")
        }, onKrack = { k ->
            k.track("HEY, YOU" to "I'M KRACK")
            k.dispatch()
            k
        })

        assertTrue(
            krackResultCodeBlock is KrackerCustom<String, String>,
            "Not expected instance of krack"
        )
    }

    @Test
    fun `krack code block without specify the destination, ie, noop`() {
        val krackResultCodeBlock = krack { k ->
            k.track("HEY, YOU" to "I'M KRACK")
            k
        }

        assertTrue(
            krackResultCodeBlock is KrackerNoop<String, String>,
            "Not expected instance of krack"
        )
    }

    @Test
    fun `krack code block without specifying the public destination`() {
        val krackResultCodeBlock = krack(
            Kracking.useConsoleLog(),
            Kracking.usePrivateStorage(File.createTempFile("krack", "_temp"))
        ) { k ->
            k.track("HEY, YOU" to "I'M KRACK")
            k
        }

        assertTrue(
            krackResultCodeBlock is KrackerPrivateStorage<String, String>,
            "Not expected instance of krack"
        )
    }

    @Test
    fun `krack code block without specifying the private destination`() {
        val krackResultCodeBlock = krack(
            Kracking.useConsoleLog(),
            Kracking.usePublicStorage(File.createTempFile("krack", "_temp"))
        ) { k ->
            k.track("HEY, YOU" to "I'M KRACK")
            k
        }

        assertTrue(
            krackResultCodeBlock is KrackerPublicStorage<String, String>,
            "Not expected instance of krack"
        )
    }

    @Test
    fun `krack code block without specifying the console log destination`() {
        val krackResultCodeBlock = krack(
            Kracking.usePrivateStorage(File.createTempFile("krack", "_temp")),
            Kracking.usePublicStorage(File.createTempFile("krack", "_temp"))
        ) { k ->
            k.track("HEY, YOU" to "I'M KRACK")
            k
        }

        assertTrue(
            krackResultCodeBlock is KrackerPublicStorage<String, String>,
            "Not expected instance of krack"
        )
    }

    @Test
    fun `krack code block with one or more track`() {
        val krackResultCodeBlock = krack(Kracking.useConsoleLog()) { k ->
            k.track("HEY, YOU" to "I'M KRACK")
            k.track("YOU" to "CAN TRACK OR")
            k.track("KRACK" to "YOUR CODE")
        }

        assertEquals(Unit, krackResultCodeBlock)
    }

    @Test
    fun `krack code block without track`() {
        val krackResultCodeBlock = krack<Unit, Nothing, Nothing>(Kracking.useConsoleLog()) { }
        assertEquals(Unit, krackResultCodeBlock)
    }

    @Test
    fun `krack code block without track and destination`() {
        val krackResultCodeBlock = krack<Unit, Nothing, Nothing> { }
        assertEquals(Unit, krackResultCodeBlock)
    }
}
