package com.example.new_compose.core.composables

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.example.new_compose.R


@Composable
fun LottieView(
    modifier: Modifier=Modifier,
    @RawRes res: Int = R.raw.error,
    iterations: Int = LottieConstants.IterateForever,
    contentScale: ContentScale = ContentScale.Fit,
    speed: Float = 1f,
    reverseOnRepeat: Boolean = false,
    color: Color? = null
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(res))
    val dynamicProperties = if(color==null) null else rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR_FILTER,
            value = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                color.hashCode(),
                BlendModeCompat.SRC_ATOP
            ),
            keyPath = arrayOf(
                "**"
            )
        )
    )
    LottieAnimation(
        composition = composition,
        iterations = iterations,
        modifier = modifier,
        dynamicProperties = dynamicProperties,
        contentScale = contentScale,
        reverseOnRepeat=reverseOnRepeat,
        speed=speed
    )
}