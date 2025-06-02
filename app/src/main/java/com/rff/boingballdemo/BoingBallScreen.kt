package com.rff.boingballdemo

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rff.boingballdemo.component.AmigaToolbar
import com.rff.boingballdemo.component.BoingBallView
import com.rff.boingballdemo.ui.theme.TopazFont
import com.rff.boingballdemo.ui.theme.backgroundColor

@Composable
fun BoingBallScreen() {
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
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 40.dp, end = 40.dp)
                .clickable { println("jebudu!!!!") },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .width(120.dp)
                    .height(40.dp),
                painter = painterResource(R.drawable.preferences),
                contentDescription = "preferences"
            )
            Text(
                text = "Preferences",
                fontFamily = TopazFont
            )
        }
    }
}
