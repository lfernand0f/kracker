package com.kracker

import com.kracker.destination.KrackerCustom
import com.kracker.destination.KrackerNoop
import com.kracker.destination.KrackerPrivateStorage
import com.kracker.destination.KrackerPublicStorage
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

class KrackTest {

    @Test fun `krack code block specifying custom destination`() {
        Kracking.useCustomStorage {
            println("Some place")
        }

        val krackResultCodeBlock = krack<String, String, KrackerTrack<String, String>>(onKrack = { k ->
            k.track("HEY, YOU" to "I'M KRACK")
            k.dispatch()
            k
        })

        assertTrue(
            krackResultCodeBlock is KrackerCustom<String, String>,
            "Not expected instance of krack"
        )
    }

    @Test fun `krack code block without specify the destination, ie, noop`() {
        val krackResultCodeBlock = krack<String, String, KrackerTrack<String, String>> { k ->
            k.track("HEY, YOU" to "I'M KRACK")
            k
        }

        assertTrue(
            krackResultCodeBlock is KrackerNoop<String, String>,
            "Not expected instance of krack"
        )
    }

    @Test fun `krack code block without specifying the public destination`(@TempDir tempDir: File) {
        Kracking.useConsoleLog()
        Kracking.usePrivateStorage(tempDir)

        val krackResultCodeBlock = krack<String, String, KrackerTrack<String, String>> { k ->
            k.track("HEY, YOU" to "I'M KRACK")
            k
        }

        assertTrue(
            krackResultCodeBlock is KrackerPrivateStorage<String, String>,
            "Not expected instance of krack"
        )
    }

    @Test fun `krack code block without specifying the private destination`(@TempDir tempDir: File) {
        Kracking.useConsoleLog()
        Kracking.usePublicStorage(tempDir)

        val krackResultCodeBlock = krack<String, String, KrackerTrack<String, String>> { k ->
            k.track("HEY, YOU" to "I'M KRACK")
            k
        }

        assertTrue(
            krackResultCodeBlock is KrackerPublicStorage<String, String>,
            "Not expected instance of krack"
        )
    }

    @Test fun `krack code block without specifying the console log destination`(@TempDir tempDir: File) {
        Kracking.usePrivateStorage(tempDir)
        Kracking.usePublicStorage(tempDir)

        val krackResultCodeBlock = krack<String, String, KrackerTrack<String, String>> { k ->
            k.track("HEY, YOU" to "I'M KRACK")
            k
        }

        assertTrue(
            krackResultCodeBlock is KrackerPublicStorage<String, String>,
            "Not expected instance of krack"
        )
    }

    @Test fun `krack code block with one or more track`() {
        Kracking.useConsoleLog()
        val krackResultCodeBlock = krack<String, String, Unit> { k ->
            k.track("HEY, YOU" to "I'M KRACK")
            k.track("YOU" to "CAN TRACK OR")
            k.track("KRACK" to "YOUR CODE")
        }

        assertEquals(Unit, krackResultCodeBlock)
    }

    @Test fun `krack code block without track`() {
        Kracking.useConsoleLog()
        val krackResultCodeBlock = krack<Nothing, Nothing, Unit> { }
        assertEquals(Unit, krackResultCodeBlock)
    }

    @Test fun `krack code block without track and destination`() {
        val krackResultCodeBlock = krack<Nothing, Nothing, Unit> { }
        assertEquals(Unit, krackResultCodeBlock)
    }

    @AfterEach fun tearDown() {
        Kracking.resetStorages()
    }
}
