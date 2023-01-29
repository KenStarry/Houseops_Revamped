package com.example.houseops_revamped.feature_home.house_view_screen.presentation.components

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.houseops_revamped.R
import com.example.houseops_revamped.core.presentation.components.CoilImage
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel
import com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.view_pager.BackAppBar
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun HouseViewPager(
    modifier: Modifier = Modifier,
    house: HouseModel,
    context: Context
) {

    Box(
        modifier = modifier
    ) {

        val pagerState = rememberPagerState()

        HorizontalPager(
            count = house.houseImageUris.size,
            state = pagerState
        ) { page ->

            CoilImage(
                context = context,
                imageUri = house.houseImageUris[page].toUri(),
                placeholder = R.drawable.houseops_dark_final,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
            )

        }

        BackAppBar(
            price = house.housePrice
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {

            HorizontalPagerIndicator(
                pagerState = pagerState,
                indicatorWidth = 6.dp,
                indicatorHeight = 6.dp,
                indicatorShape = CircleShape,
                activeColor = MaterialTheme.colorScheme.primary,
                inactiveColor = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .wrapContentSize()
                    .background(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f))
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
            )

        }

    }


}














