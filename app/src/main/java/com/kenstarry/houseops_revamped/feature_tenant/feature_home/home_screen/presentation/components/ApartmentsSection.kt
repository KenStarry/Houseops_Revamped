package com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.apartment_item.ApartmentItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ApartmentsSection(
    modifier: Modifier = Modifier,
    allApartments: List<Apartment>,
    title: String,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val listState = rememberLazyListState()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        //  title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = title,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.9f)
            )

            HomePillBtns(
                icon = null,
                endIcon = Icons.Outlined.KeyboardArrowRight,
                title = "See All",
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor,
                containerColor = MaterialTheme.colorScheme.onPrimary,
                paddingHorizontal = 4.dp,
                onClick = {}
            )
        }

        //  lazy column to display all the apartments
        LazyRow(
            content = {
                 items(allApartments) {
                    ApartmentItem(
                        modifier = Modifier
                            .size(
                                width = 250.dp,
                                height = 150.dp
                            ),
                        primaryColor = primaryColor,
                        tertiaryColor = tertiaryColor
                    )
                 }
            },
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        )

    }
}


























