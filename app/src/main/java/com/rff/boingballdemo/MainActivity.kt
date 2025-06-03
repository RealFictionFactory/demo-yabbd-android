package com.rff.boingballdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.rff.boingballdemo.ui.theme.BoingBallDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BoingBallDemoTheme {
                val navController = rememberNavController()
                Navigation(
                    navController = navController
                )
            }
        }
    }
}
