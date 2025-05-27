package com.rff.boingballdemo.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.rff.boingballdemo.audio.BoingBallAudio
import com.rff.boingballdemo.audio.BoingBallAudioImpl

@Composable
fun rememberBoingBallAudio(): BoingBallAudio {
    val ctx = LocalContext.current
    // keep one instance for the lifetime of the composition
    return remember { BoingBallAudioImpl(ctx) }
}
