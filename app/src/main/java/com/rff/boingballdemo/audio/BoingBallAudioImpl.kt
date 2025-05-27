package com.rff.boingballdemo.audio

import android.content.Context
import android.media.SoundPool
import com.rff.boingballdemo.R

class BoingBallAudioImpl(
    context: Context
) : BoingBallAudio {
    private val soundPool: SoundPool = SoundPool.Builder()
        .setMaxStreams(2).build()

    private val soundId = soundPool.load(context, R.raw.boing, 1)

    override fun play() {
        soundPool.play(
            soundId,
            1f,
            1f,
            0,
            0,
            1f,
        )
    }

    override fun playLeft() {
        soundPool.play(
            soundId,
            1f,
            0f,
            0,
            0,
            1f,
        )
    }

    override fun playRight() {
        soundPool.play(
            soundId,
            0f,
            1f,
            0,
            0,
            1f,
        )
    }
}
