package com.guru.ott_app.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerLoader() {

    val shimmerColor = listOf(
        Color.LightGray.copy(alpha = 0.8f),
        Color.LightGray.copy(alpha = 0.4f),
        Color.LightGray.copy(alpha = 0.8f)
    )
    val transition = rememberInfiniteTransition(label = "tran")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 550f,
        animationSpec = infiniteRepeatable(
            repeatMode = RepeatMode.Reverse,
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        ), label = "Animation"
    )
    val brush = Brush.linearGradient(
        colors = shimmerColor,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    ShimmerGridItem(brush)
}

@Composable
fun ShimmerEffect(
    modifier: Modifier = Modifier,
    brush: Brush,
    width: Dp,
    height: Dp,
    cornerRadius: Dp,
    alpha: Float
) {
    Spacer(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(cornerRadius))
            .background(brush)
            .alpha(alpha)
    )
}

@Composable
fun ShimmerGridItem(
    brush: Brush,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(4.dp)
        ) {
            Row(
                Modifier.fillMaxSize()
            ) {
                repeat(6) {
                    ShimmerEffect(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .background(brush),
                        brush = brush,
                        width = 100.dp,
                        height = 100.dp,
                        cornerRadius = 100.dp,
                        alpha = 0.5f
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Add some space between spacers
                }
            }
        }

        Box(
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth()
                .height(400.dp)
        ) {
            ShimmerEffect(
                modifier = Modifier.fillMaxSize(),
                brush = brush,
                width = 400.dp,
                height = 420.dp,
                cornerRadius = 2.dp,
                alpha = 0.5f
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(all = 20.dp)
        ) {
            Column {
                repeat(3) {
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxWidth((0.3f - (0.2f * it)).coerceAtLeast(0f))
                            .background(brush)
                    )

                    Row {
                        repeat(2) {
                            Box(
                                modifier = Modifier
                                    .padding(14.dp)
                                    .weight(1f)
                                    .fillMaxWidth()
                                    .height(150.dp)
                            ) {
                                ShimmerEffect(
                                    modifier = Modifier.fillMaxSize(),
                                    brush = brush,
                                    width = 150.dp,
                                    height = 150.dp,
                                    cornerRadius = 12.dp,
                                    alpha = 0.5f
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp)) // Add some space between spacers
                }
            }
        }
    }
}
