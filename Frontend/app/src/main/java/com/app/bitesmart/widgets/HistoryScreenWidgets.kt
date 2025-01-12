package com.app.bitesmart.widgets


import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.bitesmart.data.dummyData.CaptureDateData
import com.app.bitesmart.data.dummyData.getCaptureDateData


@Composable
fun HistoryColumn(
    modifier: Modifier = Modifier,
    historyList: List<CaptureDateData> = getCaptureDateData()
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(historyList) { history ->

            Surface(
                modifier = Modifier.padding(top = 8.dp)
                    .animateContentSize (),
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${history.nameOfDay}, ${history.day} ${history.monthName} ${history.year} ",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Icon(
                            Icons.Filled.ChevronRight,
                            contentDescription = "See the ingredient history"
                        )

                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHistoryColumn() {
    HistoryColumn()
}
