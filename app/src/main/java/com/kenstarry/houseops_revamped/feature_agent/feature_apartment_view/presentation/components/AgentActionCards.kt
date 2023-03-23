package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.utils.AgentApartmentConstants

@Composable
fun AgentActionCards() {

    val listState = rememberLazyListState()

    LazyRow(
        content = {
            items(AgentApartmentConstants.actionCards) { card ->
                //  action card item
                AgentActionCardItem(actionCard = card)
            }
        },
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )

}