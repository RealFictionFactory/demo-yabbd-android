package com.rff.boingballdemo.component

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rff.boingballdemo.ui.theme.TopazFont
import com.rff.boingballdemo.ui.theme.TopazFont20
import com.rff.boingballdemo.ui.theme.blackColor

@Composable
fun AmigaTextBox(
    text: String,
    osStyle: OSStyle,
    modifier: Modifier = Modifier
) {
    val textStyle = if (osStyle == OSStyle.AmigaOS13)
        LocalTextStyle.current.copy(fontFamily = TopazFont)
    else
        LocalTextStyle.current.copy(
            fontFamily = TopazFont20,
            color = blackColor
        )

    Text(
        modifier = modifier,
        text = text,
        style = textStyle
    )
}
