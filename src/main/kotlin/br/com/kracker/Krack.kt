package br.com.kracker

import br.com.kracker.destination.KrackerConsole
import br.com.kracker.destination.KrackerCustom
import br.com.kracker.destination.KrackerPrivateStorage
import br.com.kracker.destination.KrackerPublicStorage
import br.com.kracker.destination.KrackerNoop

/**
 * Executes the function [onKrack] for each destination specified in [Kracking].
 * If no destination is specified, executes the function with [KrackerNoop].
 * If no valid destination is specified, returns a default value or throws an exception with an error message.
 * @param onKrack Function to be executed for each destination.
 * @return Result of the last successful call to the function [onKrack] that was executed, or a default value if no function was successfully executed.
 */
public inline fun <A, B, T> krack(
    onKrack: KrackerTrack<A, B>.() -> T
): T {
    val destinations = mutableListOf<KrackerTrack<A, B>>()

    if (Kracking.destinations.contains(Kracking.Destination.AT_CONSOLE_LOG)) {
        destinations.add(KrackerConsole())
    }
    if (Kracking.destinations.contains(Kracking.Destination.AT_PRIVATE_STORAGE)) {
        destinations.add(KrackerPrivateStorage(Kracking.privateParentDir))
    }
    if (Kracking.destinations.contains(Kracking.Destination.AT_PUBLIC_STORAGE)) {
        destinations.add(KrackerPublicStorage(Kracking.publicParentDir))
    }
    if (Kracking.destinations.contains(Kracking.Destination.CUSTOM)) {
        destinations.add(KrackerCustom(Kracking.customDestination))
    }

    return if (destinations.isNotEmpty()) destinations.last().onKrack() else onKrack(KrackerNoop())
}
