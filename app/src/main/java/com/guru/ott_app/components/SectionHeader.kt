package com.guru.ott_app.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.guru.ott_app.ui.theme.dimens

@Composable
fun SectionHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(
            start = MaterialTheme.dimens.small1,
            top = MaterialTheme.dimens.small1,
            end = MaterialTheme.dimens.small1
        )
    )
}