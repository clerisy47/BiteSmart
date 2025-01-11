package com.app.bitesmart.onBoard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.bitesmart.widgets.IndicatorUi
import com.app.bitesmart.widgets.OnBoardButtonUi
import com.app.bitesmart.widgets.OnBoardingGraphUI
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(onFinished: () -> Unit){
    val pages: List<OnBoardingModel> = listOf(
        OnBoardingModel.FirstPages,
        OnBoardingModel.SecondPages,
        OnBoardingModel.ThirdPages,
        OnBoardingModel.FourthPages
    )

    val pagerState = rememberPagerState(initialPage = 0) {
        pages.size
    }

    val buttonState = remember{
        derivedStateOf{
            when(pagerState.currentPage){
                0 -> listOf("","Next")
                1 -> listOf("Back","Next")
                2 -> listOf("Back","Next")
                3 -> listOf("Back","Get Started")
                else -> listOf("","")
            }
        }
    }
    val scope = rememberCoroutineScope()
    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(10.dp, 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.CenterStart
                        ){
                            if(buttonState.value[0].isNotEmpty())
                                OnBoardButtonUi(
                                    text = buttonState.value[0],
                                    backgroundColor = Color.Transparent,
                                    textColor = Color.Gray
                                ){
                                    scope.launch {
                                        if(pagerState.currentPage > 0){
                                            pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                                        }
                                    }
                                }
                        }
                        Box(
//                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ){
                            IndicatorUi(pageSize = pages.size, currentPage = pagerState.currentPage)
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.CenterEnd
                        ){
                            OnBoardButtonUi(
                                text = buttonState.value[1],
                                backgroundColor = MaterialTheme.colorScheme.primary,
                                textColor = MaterialTheme.colorScheme.onPrimary
                            ) {
                                scope.launch {
                                    if(pagerState.currentPage < pages.size-1){
                                        pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                                    }else{
                                        onFinished()
                                    }
                                }
                            }
                        }
                    }
                }
            )

        },
    ){
        Surface(
            modifier = Modifier.fillMaxSize()
                .padding(it)
        ) {
            Column (
                modifier = Modifier.fillMaxSize()
            ){
                HorizontalPager(state = pagerState) { index->
                    OnBoardingGraphUI(onBoardingModel = pages[index])
                }

            }
        }
    }



}

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview(){
    OnBoardingScreen {  }
}