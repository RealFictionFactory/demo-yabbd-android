package com.rff.boingballdemo.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rff.boingballdemo.R
import com.rff.boingballdemo.ui.theme.BoingBallDemoTheme

@Composable
fun AmigaOs13CheckBox(
    isChecked: Boolean = false,
    onCheckChanged: (Boolean) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .width(30.dp)
            .height(28.dp)
            .background(color = Color.Transparent)
            .border(width = 1.dp, color = Color.White)
            .clickable(
                onClick = {
                    onCheckChanged(!isChecked)
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (isChecked) {
            Image(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = "Checked",
            )
        }
    }
}

@Preview(name = "Uncontrolled CheckBox - Unchecked")
@Composable
private fun AmigaOs13CheckBoxPreviewUncontrolledUnchecked() {
    BoingBallDemoTheme {
        Box(modifier = Modifier.background(color = Color.Blue)) {
            var previewChecked by remember { mutableStateOf(false) }
            AmigaOs13CheckBox(
                isChecked = previewChecked,
                onCheckChanged = { previewChecked = it }
            )
        }
    }
}

@Preview(name = "Uncontrolled CheckBox - Checked")
@Composable
private fun AmigaOs13CheckBoxPreviewUncontrolledChecked() {
    BoingBallDemoTheme {
        Box(modifier = Modifier.background(color = Color.Blue)) {
            var previewChecked by remember { mutableStateOf(true) }
            AmigaOs13CheckBox(
                isChecked = previewChecked,
                onCheckChanged = { previewChecked = it }
            )
        }
    }
}

@Preview(name = "Controlled CheckBox")
@Composable
private fun ControllableAmigaOs13CheckBoxPreview() {
    BoingBallDemoTheme {
        var isParentChecked by remember { mutableStateOf(false) }
        Box(modifier = Modifier.background(color = Color.Blue)) {
            AmigaOs13CheckBox(
                isChecked = isParentChecked,
                onCheckChanged = { newCheckedState ->
                    // Parent updates its state, which flows down
                    isParentChecked = newCheckedState
                }
            )
        }
    }
}
