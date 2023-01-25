package com.example.houseops_revamped.feature_home.presentation.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.feature_home.domain.model.HouseModel
import com.example.houseops_revamped.feature_home.presentation.components.house_item.HouseItem

@Composable
fun FeaturedSection(
    modifier: Modifier = Modifier,
    context: Context,
    title: String,
    houses: List<HouseModel>
) {

    Column(
        modifier = modifier
    ) {

        //  title
        Text(
            text = title,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
        )

        //  houses
        LazyRow(
            content = {

                itemsIndexed(
                    houses
                ) { index, house ->
                    HouseItem(
                        context = context,
                        house = house
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





















