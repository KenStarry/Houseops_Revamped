package com.example.houseops_revamped.feature_bookmark.presentation.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.core.domain.model.HouseCategoryModel
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.domain.use_cases.CurrentUser
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel
import com.example.houseops_revamped.feature_home.home_screen.presentation.components.house_item.HouseItem

@Composable
fun CategoryItem(
    context: Context,
    houseCategory: HouseCategoryModel,
    bookmarkedHouses: List<HouseModel>,
    currentUser: UsersCollection?
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.onSecondary)
            .padding(16.dp)
    ) {

        //  title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            Icon(
                imageVector = houseCategory.icon,
                contentDescription = "House category"
            )

            Text(
                text = houseCategory.title,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

        }

        //  another lazy column for the house items
        LazyColumn(
            content = {
                itemsIndexed(
                    bookmarkedHouses
                ) { index, house ->

                    if (house.houseCategory == houseCategory.title) {
                        HouseItem(
                            context = context,
                            house = house,
                            user = currentUser
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













