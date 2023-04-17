package com.kracker.destination

import com.kracker.KrackerTrack

class KrackerNoop<A, B> : KrackerTrack<A, B> {

    override fun track(initial: Pair<A, B>) {}

}
