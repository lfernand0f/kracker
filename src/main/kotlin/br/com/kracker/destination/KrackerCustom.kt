package br.com.kracker.destination

import br.com.kracker.KrackerSerializableData
import br.com.kracker.KrackerTrack

class KrackerCustom<A, B>(private val onDestination: (List<KrackerSerializableData<A, B>>) -> Unit) : KrackerTrack<A, B> {
    private val krackers = mutableMapOf<A, B>()

    override fun track(initial: Pair<A, B>) {
        krackers.putAll(mapOf(initial))
    }

    override fun dispatch() {
        println("com.kracker.destination.KrackerCustom has: $krackers with the following path: ${onDestination(
            krackers.map { KrackerSerializableData(key = it.key, value = it.value) }
        )}")
        krackers.clear()
    }
}
