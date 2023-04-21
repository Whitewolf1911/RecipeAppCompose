package com.alibasoglu.recipeapp.presentation.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun CircularProgressBarWithText() {
//    If you need to arrange differently on landscape and portrait mode
//    see the decoupling constraints from docs.
//    Also you can use guidelines to constrain your layouts.
    ConstraintLayout {
        val (text, progressBar) = createRefs()

        CircularProgressIndicator(modifier = Modifier.constrainAs(progressBar) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        Text(
            text = "Loading",
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.constrainAs(text) {
                top.linkTo(progressBar.bottom, margin = 4.dp)
            })
    }
}

@Preview()
@Composable
fun PreviewCircularProgressBarWithText() {
    CircularProgressBarWithText()
}
