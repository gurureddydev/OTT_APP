package com.guru.ott_app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.ott_app.R

@Composable
fun NoInternetUI(message: String,des:String, onRetry: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_wifi_off_24),
                contentDescription = "Wi-Fi off icon"
            )
            Text(
                text = message,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = des,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = "Retry icon",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .background(Color.LightGray, shape = CircleShape)
                    .clickable { onRetry() }
                    .padding(4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoInternetUIPreview() {
    NoInternetUI("No Internet", "Oops, it seems like you're offline. Please check your internet connection and try again.") {
        // Handle retry action here
    }
}
