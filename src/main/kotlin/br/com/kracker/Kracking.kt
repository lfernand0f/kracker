package br.com.kracker

import java.io.File
import java.io.Serializable

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

    private lateinit var _customDestination: (List<KrackerSerializableData<*, *>>) -> Unit
    public val customDestination: (List<KrackerSerializableData<*, *>>) -> Unit get() = _customDestination

    private var _destinations: MutableList<Destination> = mutableListOf()
    public val destinations: List<Destination> get() = _destinations

    fun useConsoleLog(): Destination {
        _destinations += Destination.AT_CONSOLE_LOG
        return Destination.AT_CONSOLE_LOG
    }

    fun usePrivateStorage(parentDir: File): Destination {
        _privateParentDir = parentDir
        _destinations += Destination.AT_PRIVATE_STORAGE
        return Destination.AT_PRIVATE_STORAGE
    }

    fun usePublicStorage(parentDir: File): Destination {
        _publicParentDir = parentDir
        _destinations += Destination.AT_PUBLIC_STORAGE
        return Destination.AT_PUBLIC_STORAGE
    }

    fun useCustomStorage(onChoose: (List<KrackerSerializableData<*, *>>) -> Unit): Destination {
        _customDestination = onChoose
        _destinations += Destination.CUSTOM
        return Destination.CUSTOM
    }

    fun resetStorages() {
        _destinations = mutableListOf(Destination.NONE)
        _customDestination = {}
        if (::_privateParentDir.isInitialized) _privateParentDir.deleteRecursively()
        if (::_publicParentDir.isInitialized) _publicParentDir.deleteRecursively()
    }

    enum class Destination {
        AT_CONSOLE_LOG,
        AT_PRIVATE_STORAGE,
        AT_PUBLIC_STORAGE,
        CUSTOM,
        NONE;
    }
}

data class KrackerSerializableData<A, B>(val key: A, val value: B)