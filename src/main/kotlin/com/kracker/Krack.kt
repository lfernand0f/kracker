package com.kracker

import com.kracker.destination.KrackerConsole
import com.kracker.destination.KrackerCustom
import com.kracker.destination.KrackerPrivateStorage
import com.kracker.destination.KrackerPublicStorage
import com.kracker.destination.KrackerNoop


/**
 * Executes the function [onKrack] for each destination specified in [Kracking].
 * If no destination is specified, executes the function with [KrackerNoop].
 * If no valid destination is specified, returns a default value or throws an exception with an error message.
 * @param onKrack Function to be executed for each destination.
 * @return Result of the last successful call to the function [onKrack] that was executed, or a default value if no function was successfully executed.
 */
inline fun <A, B, T> krack(onKrack: (KrackerTrack<A, B>) -> T): T {
    var krackResult: T? = null

    if (Kracking.destinations.contains(Kracking.Destination.AT_CONSOLE_LOG)) {
        krackResult = onKrack(KrackerConsole())
    }

    if (Kracking.destinations.contains(Kracking.Destination.AT_PRIVATE_STORAGE)) {
        krackResult = onKrack(KrackerPrivateStorage(Kracking.privateParentDir))
    }

    if (Kracking.destinations.contains(Kracking.Destination.AT_PUBLIC_STORAGE)) {
        krackResult = onKrack(KrackerPublicStorage(Kracking.publicParentDir))
    }

    if (Kracking.destinations.contains(Kracking.Destination.CUSTOM)) {
        krackResult = onKrack(KrackerCustom(Kracking.customDestination))
    }

    return krackResult ?: onKrack(KrackerNoop())
}
