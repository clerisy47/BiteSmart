package com.app.bitesmart.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.bitesmart.R

@Composable
fun LogInScreen(modifier: Modifier = Modifier) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    Surface(
        modifier = modifier
    ) {
        Column {
            Image(
                painter = painterResource(R.drawable.log_in_img_banner),
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(
                    text = stringResource(R.string.welcome_to_bitesmart),
                    style = MaterialTheme.typography.titleLarge
                )

                Image(
                    painter = painterResource(R.drawable.log_in_img_avatar),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(24.dp)
                        .size(size = 100.dp)
                        .clip(shape = CircleShape)

                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(space = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {

                    OutlinedTextField(
                        value = username.value,
                        onValueChange = { username.value = it },
                        label = { Text(text = stringResource(R.string.username), style = MaterialTheme.typography.labelLarge) },
                    )
                    OutlinedTextField(
                        value = password.value,
                        onValueChange = { password.value = it },
                        label = { Text(text = stringResource(R.string.password), style = MaterialTheme.typography.labelLarge) },
                    )

                    Text(
                        text = stringResource(R.string.forget_password),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .align(alignment = Alignment.End)
                            .clickable {
                                // Todo: provide a way to get the password
                            }
                    )
                    Button(
                        onClick = {
                            //Todo: go to user dashboard after verifying
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.log_in)
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 24.dp)
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        text = stringResource(R.string.or),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    HorizontalDivider(
                        modifier = Modifier.weight(2f)
                    )
                }
                Button(
                    onClick = {
                        //Todo: go to the sign up page to sign in
                    },
                ) {
                    Text(
                        text = stringResource(R.string.sign_up)
                    )
                }

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LogInScreenPreview() {
    LogInScreen()
}