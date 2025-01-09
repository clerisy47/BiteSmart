package com.app.bitesmart.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.app.bitesmart.R


@Composable
fun CustomTextField(label: String, textState: MutableState<String>, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge
            )
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun TextFieldComponentPreview() {
    val textState = remember { mutableStateOf("") }
    CustomTextField(label = stringResource(R.string.username), textState = textState)
}
