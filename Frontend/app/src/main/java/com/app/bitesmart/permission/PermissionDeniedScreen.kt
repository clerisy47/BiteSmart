package com.app.bitesmart.permission

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PermissionDeniedScreen(
    onOpenSettings: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Camera permission is essential for scanning. Please grant permission.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(onClick = onOpenSettings) {
            Text("Grant Permission")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PermissionDeniedPreview(){
    PermissionDeniedScreen {  }
}