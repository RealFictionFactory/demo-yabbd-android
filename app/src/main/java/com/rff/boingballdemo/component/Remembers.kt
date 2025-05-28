package com.rff.boingballdemo.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import com.rff.boingballdemo.audio.BoingBallAudio
import com.rff.boingballdemo.audio.BoingBallAudioImpl
import com.rff.boingballdemo.audio.NoOpBoingBallAudioImpl

@Composable
fun rememberBoingBallAudio(): BoingBallAudio {
    val ctx = LocalContext.current
    val isInPreview = LocalInspectionMode.current

    val audioPlayer = remember {
        if (isInPreview) {
            NoOpBoingBallAudioImpl()
        } else {
            BoingBallAudioImpl(ctx)
        }
    }

    if (!isInPreview) {
        DisposableEffect(audioPlayer) {
            onDispose {
                audioPlayer.release()
            }
        }
    }

    return audioPlayer
}
