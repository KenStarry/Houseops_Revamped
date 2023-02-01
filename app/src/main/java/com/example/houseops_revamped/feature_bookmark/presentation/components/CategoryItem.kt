package com.example.houseops_revamped.feature_bookmark.presentation.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.houseops_revamped.core.domain.model.HouseCategoryModel
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel
import com.example.houseops_revamped.feature_home.home_screen.presentation.components.house_item.HouseItem
import com.example.houseops_revamped.navigation.Direction

@Composable
fun CategoryItem(
    context: Context,
    navHostController: NavHostController,
    houseCategory: HouseCategoryModel,
    bookmarkedHouses: List<HouseModel>,
    currentUser: UsersCollection?
) {

    val direction = Direction(navHostController)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp),
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
                    .background(MaterialTheme.colorScheme.tertiary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = houseCategory.icon,
                    contentDescription = "House category",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Text(
                text = houseCategory.title,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )

            Divider(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.1f)),
                thickness = 1.dp
            )

        }

        //  another lazy column for the house items
        LazyColumn(
            content = {
                itemsIndexed(
                    bookmarkedHouses
                ) { index, house ->

                    if (house.houseCategory == houseCategory.title) {
                        HouseItemAlt(
                            context = context,
                            house = house,
                            user = currentUser,
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .fillMaxWidth()
                                .height(150.dp)
                                .background(MaterialTheme.colorScheme.onSecondary)
                                .clickable {
                                    //  open house view Screen
                                    direction.navigateToHouseView(
                                        house.houseApartmentName, house.houseCategory
                                    )
                                }
                                .padding(8.dp)
                        )
                    }
                }
            },
            state = rememberLazyListState(),
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        )
    }
}













