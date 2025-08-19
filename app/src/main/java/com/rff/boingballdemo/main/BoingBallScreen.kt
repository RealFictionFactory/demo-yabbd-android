package com.rff.boingballdemo.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rff.boingballdemo.main.BoingBallViewModel
import com.rff.boingballdemo.R
import com.rff.boingballdemo.component.AmigaToolbar
import com.rff.boingballdemo.component.BoingBallView
import com.rff.boingballdemo.ui.theme.BoingBallDemoTheme
import com.rff.boingballdemo.ui.theme.TopazFont
import com.rff.boingballdemo.ui.theme.backgroundColor
import org.koin.androidx.compose.koinViewModel

@Composable
fun BoingBallScreenRoot(
    viewModel: BoingBallViewModel = koinViewModel(),
    onPreferencesClick: () -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    BoingBallScreen(
        state = state,
        onAction = { action ->
            when (action) {
                BoingBallAction.Preferences -> {
                    onPreferencesClick()
                }
            }
        }
    )
}

@Composable
fun BoingBallScreen(
    state: BoingBallState,
    onAction: (BoingBallAction) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .windowInsetsPadding(WindowInsets.safeDrawing),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(horizontal = 2.dp)
                .padding(bottom = 2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AmigaToolbar(stringResource(R.string.app_full_name))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = backgroundColor),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BoingBallView(
                    modifier = Modifier.padding(8.dp),
                    themeColor = state.themeColor,
                    altColor = state.altColor,
                    drawBorders = state.drawBorders,
                )
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 40.dp, end = 40.dp)
                .clickable { onAction(BoingBallAction.Preferences) },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .width(120.dp)
                    .height(40.dp),
                painter = painterResource(R.drawable.preferences),
                contentDescription = stringResource(R.string.preferences)
            )
            Text(
                text = stringResource(R.string.preferences),
                fontFamily = TopazFont
            )
        }
    }
}

private val previewState = BoingBallState(
    themeColor = Color.Red,
    altColor = Color.White,
    drawBorders = false,
)

@Preview
@Composable
private fun BoingBallScreenPreview() {
    BoingBallDemoTheme {
        BoingBallScreen(previewState)
    }
}

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun BoingBallScreenLandscapePreview() {
    BoingBallDemoTheme {
        BoingBallScreen(previewState)
    }
}
