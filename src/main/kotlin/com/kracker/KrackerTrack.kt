package com.kracker

interface KrackerTrack<A, B> {
    fun track(initial: Pair<A, B>)
    fun dispatch() {}
}
