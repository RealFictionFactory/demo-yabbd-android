package com.rff.boingballdemo.audio

// To enable compose Preview we need dummy implementation of BoingBallAudio
class NoOpBoingBallAudioImpl : BoingBallAudio {
    override fun play() { }

    override fun playLeft() { }

    override fun playRight() { }

    override fun release() { }
}
