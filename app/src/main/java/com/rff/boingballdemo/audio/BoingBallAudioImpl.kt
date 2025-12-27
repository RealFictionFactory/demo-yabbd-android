package com.rff.boingballdemo.audio

import android.content.Context
import android.media.SoundPool
import android.util.Log
import com.rff.boingballdemo.R

class BoingBallAudioImpl(
    context: Context
) : BoingBallAudio {
    private val soundPool: SoundPool = SoundPool.Builder()
        .setMaxStreams(2).build()

    private var soundId: Int = 0
    private var isSoundLoaded: Boolean = false

    init {
        // Set up load complete listener
        soundPool.setOnLoadCompleteListener { _, sampleId, status ->
            if (status == 0) {
                // Success
                isSoundLoaded = true
                Log.d(TAG, "Sound loaded successfully: $sampleId")
            } else {
                // Failed to load
                Log.e(TAG, "Failed to load sound. Status: $status")
            }
        }

        // Load the sound
        soundId = soundPool.load(context, R.raw.boing, 1)

        // Check if load was initiated successfully
        if (soundId == 0) {
            Log.e(TAG, "Failed to initiate sound loading. SoundId is 0.")
        }
    }

    override fun play() {
        if (!canPlaySound()) return

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
        if (!canPlaySound()) return

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
        if (!canPlaySound()) return

        soundPool.play(
            soundId,
            0f,
            1f,
            0,
            0,
            1f,
        )
    }

    override fun release() {
        soundPool.release()
    }

    private fun canPlaySound(): Boolean {
        if (soundId == 0) {
            Log.w(TAG, "Cannot play sound: soundId is 0 (load failed)")
            return false
        }
        if (!isSoundLoaded) {
            Log.w(TAG, "Cannot play sound: sound is still loading")
            return false
        }
        return true
    }

    companion object {
        private const val TAG = "BoingBallAudio"
    }
}
