package com.plcoding.cryptotracker.crypto.presentation.coin_detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cryptotracker.R
import com.plcoding.cryptotracker.crypto.presentation.coin_list.CoinListState
import com.plcoding.cryptotracker.crypto.presentation.models.CoinUi
import com.plcoding.cryptotracker.crypto.presentation.models.DisplayableNumber
import com.plcoding.cryptotracker.crypto.presentation.models.toDisplayableNumber

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailScreen( state: CoinListState,modifier: Modifier = Modifier) {
    val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    if (state.isLoading) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (state.selectedCoin != null) {
        val coin = state.selectedCoin
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surfaceContainer)
                .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Icon(
                imageVector = ImageVector.vectorResource(id = coin.iconRes),
                contentDescription = coin.name,
                tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(90.dp).padding(bottom = 20.dp)
            )
            Text(
                coin.name, fontSize = 35.sp,
                fontWeight = FontWeight.Bold, color = contentColor,modifier = Modifier.padding(bottom = 20.dp)
            )
            Text(
                coin.symbol, fontSize = 20.sp,
                fontWeight = FontWeight.Light, color = contentColor
            )







            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CoinCard(
                    contentColor = contentColor,
                    coin = coin,
                    title = "Market cap",
                    value = coin.marketCapUsd,
                    iconId = R.drawable.stock,
                )

                Spacer(modifier = Modifier.width(10.dp))


                CoinCard(
                    contentColor = contentColor,
                    coin = coin,
                    title = "Price",
                    value = coin.priceUsd,
                    R.drawable.dollar,
                )


                Spacer(modifier = Modifier.width(10.dp))

                val change = (coin.priceUsd.value * (coin.changePercent24Hr.value / 100)).toDisplayableNumber()

                CoinCard(
                    contentColor = if (coin.changePercent24Hr.value > 0)contentColor else MaterialTheme.colorScheme.error,

                    coin = coin,
                    title = "Change last 24hr",
                    value = change,
                    iconId = if (coin.changePercent24Hr.value > 0) R.drawable.trending else R.drawable.trending_down,
                )


            }
        }

   }
}





@Composable
fun CoinCard(
    contentColor: Color,
    coin: CoinUi,
    title: String,
    value: DisplayableNumber,
    iconId: Int,
) {

    Card(
        modifier = Modifier
            .padding(top = 20.dp)
            .shadow(
                elevation = 15.dp,
                shape = RectangleShape,
                ambientColor = MaterialTheme.colorScheme.primary,
                spotColor = MaterialTheme.colorScheme.primary,
            )

            .border(
                width = 2.dp, shape = RoundedCornerShape(2.dp),
                color = Color.Green
            ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier

                .padding(horizontal = if(title == "Change last 24hr")20.dp else 40.dp, vertical = 20.dp)
        ) {
            AnimatedContent(targetState = iconId, label = "IconAnimation") {

                Icon(
                    imageVector = ImageVector.vectorResource(id = it),
                    contentDescription = coin.name,
                    tint = contentColor,
                    modifier = Modifier
                        .size(70.dp)
                        .padding(bottom = 20.dp)
                )
            }

            AnimatedContent(targetState = value.formatted, label = "IconAnimation") { formateText ->


                Text(

                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = contentColor
                            )
                        ) {
                            append("$ ")
                        }
                        append(formateText)
                    },
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = contentColor,
                    ),
                    modifier = Modifier.padding(bottom = 15.dp)


                )

            }


            Text(
                title, fontSize = 14.sp,
                fontWeight = FontWeight.Light, color = contentColor
            )
        }

    }
}




