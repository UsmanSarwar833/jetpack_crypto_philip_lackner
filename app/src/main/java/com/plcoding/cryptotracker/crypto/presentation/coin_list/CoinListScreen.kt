package com.plcoding.cryptotracker.crypto.presentation.coin_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import com.plcoding.cryptotracker.crypto.presentation.coin_list.components.CoinListItem


@Composable
fun CoinListScreen(state: CoinListState, modifier: Modifier = Modifier,onAction: (CoinListAction) -> Unit) {

    if(state.isLoading){
        Box(modifier = modifier.fillMaxSize(1f), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color.Red,) }
    }else{
        LazyColumn(modifier = Modifier.padding(top = 60.dp)
        ) {
            items(state.coins){coinUi ->
             CoinListItem(coinUi = coinUi, onClick = {onAction(CoinListAction.OnCoinClick(coinUi))}, modifier = Modifier.fillMaxWidth())
             HorizontalDivider()

            }
        }
    }
}
