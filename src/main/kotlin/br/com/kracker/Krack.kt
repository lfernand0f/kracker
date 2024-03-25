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
    operation: KrackerTrack<A, B>.() -> T
): T {
    val destinations = when {
        Kracking.Destination.AT_CONSOLE_LOG in Kracking.destinations -> setOf(KrackerConsole<A, B>())
        Kracking.Destination.AT_PRIVATE_STORAGE in Kracking.destinations -> setOf(KrackerPrivateStorage<A, B>(Kracking.privateParentDir))
        Kracking.Destination.AT_PUBLIC_STORAGE in Kracking.destinations -> setOf(KrackerPublicStorage<A, B>(Kracking.publicParentDir))
        Kracking.Destination.CUSTOM in Kracking.destinations -> setOf(KrackerCustom<A, B>(Kracking.customDestination))
        else -> emptySet()
    }

    return destinations.map(operation).last()
}
