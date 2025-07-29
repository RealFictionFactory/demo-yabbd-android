package com.rff.boingballdemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rff.boingballdemo.component.AmigaOs13Button
import com.rff.boingballdemo.component.AmigaToolbar
import com.rff.boingballdemo.component.AmigaOs13CheckBox
import com.rff.boingballdemo.component.AmigaOs13ColorPicker
import com.rff.boingballdemo.ui.theme.BoingBallDemoTheme
import com.rff.boingballdemo.ui.theme.amigaOs13Blue
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
    var drawFrameCheckState by remember { mutableStateOf(false) }

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
                    .background(color = amigaOs13Blue)
                    .padding(16.dp)
            ) {
                Text(text = "Pick main BB color")
                AmigaOs13ColorPicker()
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Pick alternate BB color")
                AmigaOs13ColorPicker()
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Draw BB square frames")
                    Spacer(modifier = Modifier.width(8.dp))
                    AmigaOs13CheckBox(
                        isChecked = drawFrameCheckState,
                        onCheckChanged = { newState ->
                            drawFrameCheckState = newState
                        }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                AmigaOs13Button(
                    text = "Bring BB defaults",
                    onClick = {}
                )
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
