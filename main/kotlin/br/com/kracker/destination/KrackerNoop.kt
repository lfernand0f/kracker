package br.com.kracker.destination

import br.com.kracker.KrackerTrack

class KrackerNoop<A, B> : KrackerTrack<A, B> {

    override fun track(initial: Pair<A, B>) {}

}
