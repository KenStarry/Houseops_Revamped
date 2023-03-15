package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.presentation.components.HouseItemAlt
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AgentApartmentHouses(
    context: Context,
    user: UsersCollection?,
    houses: List<HouseModel>,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val listState = rememberLazyListState()

    LazyColumn(
        content = {
            items(houses) { house ->

                HouseItemAlt(
                    context = context,
                    house = house,
                    user = user,
                    snackbarHostState = null,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(MaterialTheme.colorScheme.onSecondary)
                        .clickable {
                        }
                        .padding(8.dp)
                        .animateItemPlacement(),
                )

            }
        },
        state = listState,
        modifier = Modifier
            .fillMaxSize()
    )

}