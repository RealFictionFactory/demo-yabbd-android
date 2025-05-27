package com.rff.boingballdemo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rff.boingballdemo.ui.theme.BoingBallDemoTheme
import com.rff.boingballdemo.ui.theme.backgroundColor

@Composable
fun BoingBallView(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .aspectRatio(4f / 3f)
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        BoingBallBackground(
            modifier = modifier
                .fillMaxSize(),
        )

        BoingBall(
            modifier = modifier
                .fillMaxSize(),
        )
    }
}

@Preview
@Composable
private fun BoingBallViewPreview() {
    BoingBallDemoTheme {
        BoingBallView()
    }
}
