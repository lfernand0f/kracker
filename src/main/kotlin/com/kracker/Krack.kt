package com.kracker

import com.kracker.destination.*


/**
 * Executes the function [onKrack] for each destination specified in [targetsToKrack].
 * If no destination is specified, executes the function with [KrackerNoop].
 * If no valid destination is specified, returns a default value or throws an exception with an error message.
 * @param targetsToKrack List of destinations for which the function [onKrack] should be executed.
 * @param onKrack Function to be executed for each destination in [targetsToKrack].
 * @return Result of the last successful call to the function [onKrack] that was executed, or a default value if no function was successfully executed.
 */
inline fun <T, A, B> krack(
    vararg targetsToKrack: Kracking.Destination = arrayOf(Kracking.Destination.NONE),
    onKrack: (KrackerTrack<A, B>) -> T
): T {
    var krackResult: T? = null

    if (targetsToKrack.contains(Kracking.Destination.AT_CONSOLE_LOG)) {
        krackResult = onKrack(KrackerConsole())
    }

    if (targetsToKrack.contains(Kracking.Destination.AT_PRIVATE_STORAGE)) {
        krackResult = onKrack(KrackerPrivateStorage(Kracking.privateParentDir))
    }

    if (targetsToKrack.contains(Kracking.Destination.AT_PUBLIC_STORAGE)) {
        krackResult = onKrack(KrackerPublicStorage(Kracking.publicParentDir))
    }

    if (targetsToKrack.contains(Kracking.Destination.CUSTOM)) {
        krackResult = onKrack(KrackerCustom(Kracking.customDestination))
    }

    return krackResult ?: onKrack(KrackerNoop())
}
