package com.plcoding.cryptotracker.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.plcoding.cryptotracker.crypto.presentation.models.DisplayableNumber
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun PriceChange(change:DisplayableNumber,modifier: Modifier = Modifier,) {

    val contentColor = if(isSystemInDarkTheme()){Color.White}else{Color.White}

    val backGroundColor = if (change.value <  0.0) {
        MaterialTheme.colorScheme.onErrorContainer }
        else { Color.Green }

        Row(modifier = modifier
            .clip(shape = RoundedCornerShape(100f))
            .background(color = backGroundColor)
            .padding(horizontal = 8.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = if (change.value < 0.0)
                    Icons.Default.KeyboardArrowDown
                else Icons.Filled.KeyboardArrowUp, contentDescription = "",modifier = Modifier.size(20.dp), tint = contentColor
            )
            Text("${change.formatted} %",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = contentColor
                )
            )
        }
    }

@PreviewLightDark

@Composable
private fun PriceChangePreview() {
    CryptoTrackerTheme{
        PriceChange(
            change = DisplayableNumber(
                value = 2.43,
                formatted = "2.43"
            )
        )
    }
}