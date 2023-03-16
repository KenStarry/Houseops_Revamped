package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.House
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        //  title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(35.dp)
                    .background(tertiaryColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.House,
                    contentDescription = "Houses",
                    tint = primaryColor
                )
            }

            Text(
                text = "Houses",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
            )

        }

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
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        )

    }

}