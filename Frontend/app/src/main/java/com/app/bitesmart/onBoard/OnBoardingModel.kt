package com.app.bitesmart.onBoard

import androidx.annotation.DrawableRes
import com.app.bitesmart.R

sealed class OnBoardingModel (
    @DrawableRes val image: Int,
    val title: String,
    val description: String,
){
    data object FirstPages : OnBoardingModel(
        image = R.drawable.log_in_img_avatar,
        title = "Elevate Your Nutrition with BiteSmart!",
        description = "Discover a smarter way to manage your nutrition. Effortlessly scan ingredients, receive tailored health insights, and monitor your nutrient intake to achieve your wellness goals."
    )

    data object SecondPages : OnBoardingModel(
        image = R.drawable.onboard_2_img,
        title = "Effortless Ingredient Scanning",
        description = "Empower your choices by scanning product labels with ease. Detect harmful substances, avoid banned additives, and make informed decisions for a healthier lifestyle."
    )

    data object ThirdPages : OnBoardingModel(
        image = R.drawable.onboard_3_img,
        title = "Personalized Health Compatibility",
        description = "Ensure your food choices align with your health needs. Check for safety against diabetes, allergies, and dietary restrictions, giving you confidence and peace of mind."
    )

    data object FourthPages : OnBoardingModel(
        image = R.drawable.onboard_4_img,
        title = "Monitor Your Nutrient Journey",
        description = "Keep track of your monthly nutrient intake. Gain insights on essential vitamins and minerals, identify deficiencies, and stay on top of your health with personalized recommendations."
    )

}