package com.plcoding.cryptotracker.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cryptotracker.crypto.domain.Coin
import com.plcoding.cryptotracker.crypto.presentation.models.CoinUi
import com.plcoding.cryptotracker.crypto.presentation.models.toCoinUi
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun CoinListItem (coinUi: CoinUi,onClick:() -> Unit,modifier: Modifier) {
    val contentColor = if(isSystemInDarkTheme()){Color.White}else{Color.Black}
        Row(
            modifier = modifier
                .clickable(onClick = onClick)
                .fillMaxWidth()
                .height(100.dp)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = coinUi.iconRes),
                    contentDescription = coinUi.name,
                    tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(85.dp)
                )

                Column(
                   modifier = Modifier.weight(1f)
                ) {
                    Text(
                        coinUi.symbol, fontSize = 20.sp,
                        fontWeight = FontWeight.Bold, color = contentColor)
                    Text(coinUi.name,fontSize = 14.sp,
                        fontWeight = FontWeight.Light, color = contentColor)
                }


            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(

                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = contentColor)) {
                            append("$ ") }
                        append(coinUi.priceUsd.formatted.toString())
                                         },
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = contentColor,),
                    modifier = Modifier.padding(bottom = 10.dp)

                )

                PriceChange(change = coinUi.changePercent24Hr,)


            }
        }
    }


@PreviewLightDark
@Composable
private fun CoinListItemPreview() {
    CryptoTrackerTheme {
        CoinListItem(coinUi = previewCoin, onClick = { }, modifier = Modifier.background( color = MaterialTheme.colorScheme.background))

    }
}


///here we are converting coin data class into coinui data class for the ui
internal val previewCoin = Coin(
    id = "1",
    rank = 1,
    name = "Bitcoin",
    symbol = "BTC",
    marketCapUsd = 1241273958896.75,
    priceUsd = 62828.15,
    changePercent24Hr = 0.1,
).toCoinUi()









