package com.kracker

import java.io.File

object Kracking {
    private lateinit var _privateParentDir: File
    public val privateParentDir: File get() {
        if (!_privateParentDir.exists()) _privateParentDir.mkdirs()
        return _privateParentDir
    }

    private lateinit var _publicParentDir: File
    public val publicParentDir: File get() {
        if (!_publicParentDir.exists()) _publicParentDir.mkdirs()
        return _publicParentDir
    }

    private lateinit var _customDestination: () -> Unit
    public val customDestination: () -> Unit get() = _customDestination

    fun useConsoleLog(): Destination = Destination.AT_CONSOLE_LOG

    fun usePrivateStorage(parentDir: File): Destination {
        _privateParentDir = parentDir
        return Destination.AT_PRIVATE_STORAGE
    }

    fun usePublicStorage(parentDir: File): Destination {
        _publicParentDir = parentDir
        return Destination.AT_PUBLIC_STORAGE
    }

    fun chooseDestination(onChoose: () -> Unit): Destination {
        _customDestination = onChoose
        return Destination.CUSTOM
    }

    enum class Destination {
        AT_CONSOLE_LOG,
        AT_PRIVATE_STORAGE,
        AT_PUBLIC_STORAGE,
        CUSTOM,
        NONE;
    }
}
