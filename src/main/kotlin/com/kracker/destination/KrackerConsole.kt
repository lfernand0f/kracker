package com.kracker.destination

import com.kracker.KrackerTrack

class KrackerConsole<A, B> : KrackerTrack<A, B> {
    private val krackers = mutableMapOf<A, B>()

    override fun track(initial: Pair<A, B>) {
        krackers.putAll(mapOf(initial))
    }

    override fun dispatch() {
        println("console.kracker: $krackers")
        krackers.clear()
    }
}
