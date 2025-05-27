package com.rff.boingballdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rff.boingballdemo.ui.theme.BoingBallDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BoingBallDemoTheme {
                BoingBallScreenRoot()
            }
        }
    }
}

@Preview
@Composable
private fun BoingBallScreenPreview() {
    BoingBallDemoTheme {
        BoingBallScreenRoot()
    }
}

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun BoingBallScreenLandscapePreview() {
    BoingBallDemoTheme {
        BoingBallScreenRoot()
    }
}
