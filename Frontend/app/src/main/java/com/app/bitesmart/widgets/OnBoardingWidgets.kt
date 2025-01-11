package com.app.bitesmart.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.bitesmart.onBoard.OnBoardingModel

@Composable
fun OnBoardingGraphUI(onBoardingModel: OnBoardingModel){
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.fillMaxWidth().size(80.dp))
        Image(
            painter = painterResource(id = onBoardingModel.image),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
                .padding(32.dp,0.dp),
            alignment = Alignment.Center
        )
        Spacer(Modifier.size(50.dp))
        Text(
            text = onBoardingModel.title,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(
            modifier = Modifier.fillMaxWidth()
                .size(16.dp)
        )
        Text(
            text = onBoardingModel.description,
            modifier = Modifier.fillMaxWidth()
                .padding(15.dp, 0.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun IndicatorUi(
    pageSize: Int,
    currentPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.secondary,
    unselectedColor: Color = MaterialTheme.colorScheme.secondaryContainer
){
    Row (horizontalArrangement = Arrangement.SpaceBetween){
        repeat(pageSize){
            Box(modifier = Modifier.height(16.dp)
                .width(width = if(it==currentPage) 32.dp else 14.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = if(it == currentPage) selectedColor else unselectedColor)
            )
            Spacer(modifier = Modifier.size(2.5.dp))
        }
    }
}

@Composable
fun OnBoardButtonUi(
    text: String = "Next",
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    fontSize: Int = 14,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            style = textStyle
        )
    }
}

@Preview
@Composable
fun NextButton(){
    OnBoardButtonUi(text = "Get Started") {

    }
}
@Preview
@Composable
fun BackButton(){
    OnBoardButtonUi(
        text = "Back",
        backgroundColor = Color.Transparent,
        textColor = Color.Gray,
        textStyle = MaterialTheme.typography.bodySmall,
        fontSize = 13
        ) {

    }
}

@Preview(showBackground = true)
@Composable
fun IndicatorPreview1(){
    IndicatorUi(pageSize = 4, currentPage = 0)
}
//@Preview(showBackground = true)
//@Composable
//fun IndicatorPreview2(){
//    IndicatorUi(pageSize = 4, currentPage = 1)
//}
//@Preview(showBackground = true)
//@Composable
//fun IndicatorPreview3(){
//    IndicatorUi(pageSize = 4, currentPage = 2)
//}
//@Preview(showBackground = true)
//@Composable
//fun IndicatorPreview4(){
//    IndicatorUi(pageSize = 4, currentPage = 3)
//}
//
@Preview(showBackground = true)
@Composable
fun OnBoardingPreviewFirst(){
    OnBoardingGraphUI(onBoardingModel = OnBoardingModel.FirstPages)
}
//@Preview(showBackground = true)
//@Composable
//fun OnBoardingPreviewSecond(){
//    OnBoardingGraphUI(onBoardingModel = OnBoardingModel.SecondPages)
//}
//@Preview(showBackground = true)
//@Composable
//fun OnBoardingPreviewThird(){
//    OnBoardingGraphUI(onBoardingModel = OnBoardingModel.ThirdPages)
//}
//@Preview(showBackground = true)
//@Composable
//fun OnBoardingPreviewFourth(){
//    OnBoardingGraphUI(onBoardingModel = OnBoardingModel.FourthPages)
//}