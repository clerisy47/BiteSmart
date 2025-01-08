package com.app.bitesmart.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.bitesmart.R

@Composable
fun BottomAppBar() {

            BottomAppBar(
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = "Localized description"
                            )
                        }
                        IconButton(onClick = { /* do something */ }) {
                            Image(
                                painter = painterResource(R.drawable.log_in_img_avatar),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(size = 80.dp)
                                    .clip(shape = CircleShape)

                            )
                        }
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                Icons.Filled.ShoppingCart,
                                contentDescription = "Localized description",
                            )
                        }
                    }
                   
                }
            )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(){
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onSurface,
                    ),
                    title = {
                        Text(
                            "Food Scan",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Navigate to previous screen"
                            )
                        }
                    },
                    scrollBehavior = null,
                )

}
@Preview
@Composable
fun WidgetPreview(){
//    BottomAppBar()
    TopAppBar()
}