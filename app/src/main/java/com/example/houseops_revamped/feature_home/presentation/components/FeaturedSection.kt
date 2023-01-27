package com.example.houseops_revamped.feature_home.presentation.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseops_revamped.feature_home.domain.model.HouseModel
import com.example.houseops_revamped.feature_home.presentation.components.house_item.HouseItem
import com.example.houseops_revamped.feature_home.presentation.viewmodel.HomeViewModel

@Composable
fun FeaturedSection(
    modifier: Modifier = Modifier,
    context: Context,
    title: String,
    houses: ArrayList<HouseModel>,
    email: String
) {

    val homeVM: HomeViewModel = hiltViewModel()
    homeVM.getHouses()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        //  title
        Text(
            text = title,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.9f)
        )

        //  houses
        LazyRow(
            content = {

                itemsIndexed(
                    houses
                ) { index, house ->
                    HouseItem(
                        context = context,
                        house = house,
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .size(
                                width = 190.dp,
                                height = 260.dp
                            )
                            .background(MaterialTheme.colorScheme.onSecondary)
                            .padding(8.dp),
                        email = email
                    )
                }
            },
            state = rememberLazyListState(),
            contentPadding = PaddingValues(vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .wrapContentSize()
        )

    }

}





















