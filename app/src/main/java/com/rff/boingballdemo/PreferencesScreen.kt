package com.rff.boingballdemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rff.boingballdemo.component.AmigaToolbar
import com.rff.boingballdemo.component.ColorPicker
import com.rff.boingballdemo.ui.theme.BoingBallDemoTheme
import com.rff.boingballdemo.ui.theme.backgroundColor

/**
 * possible settings to change:
 * ASAP:
 * - Boing Ball colors (main [red, blue, green] and alternate [white, other?])
 * - Draw Boing Ball square frames (true/false)
 * IN FUTURE:
 * - Boing Ball segments number
 * - OS 1.3 / 2.0+ - changes toolbar and font
 */

@Composable
fun PreferencesScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .windowInsetsPadding(WindowInsets.safeDrawing),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(horizontal = 2.dp)
                .padding(bottom = 2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AmigaToolbar(stringResource(R.string.preferences))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = backgroundColor)
            ) {
                Text(text = "Pick main BB color")
                ColorPicker()
                Button(onClick = {}) {
                    Text(text = "Bring BB original look")
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreferencesScreenPreview() {
    BoingBallDemoTheme {
        PreferencesScreen()
    }
}
