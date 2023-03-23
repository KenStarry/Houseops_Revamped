package com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.presentation.components.view_pager

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.kenstarry.houseops_revamped.core.presentation.components.CoilImage
import com.kenstarry.houseops_revamped.core.presentation.components.Lottie
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun HouseViewPager(
    modifier: Modifier = Modifier,
    house: HouseModel,
    context: Context,
    navHostController: NavHostController,
    primaryColor: Color,
    tertiaryColor: Color,
) {

    Box(
        modifier = modifier
    ) {

        val pagerState = rememberPagerState()

        if (house.houseImageUris.isEmpty()) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Lottie(
                    rawFile = com.kenstarry.houseops_revamped.R.raw.search_empty,
                    isPlaying = true,
                    iterations = 1,
                    modifier = Modifier
                        .fillMaxSize(0.7f)
                )

                Text(
                    text = "No Images Found",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f)
                )
            }

        } else {

            HorizontalPager(
                count = house.houseImageUris.size,
                state = pagerState
            ) { page ->
                CoilImage(
                    context = context,
                    imageUri = house.houseImageUris[page].uri.toUri(),
                    placeholder = com.kenstarry.houseops_revamped.R.drawable.houseops_dark_final,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                )
            }

        }

        BackAppBar(
            price = house.housePrice,
            navHostController = navHostController
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
                activeColor = primaryColor,
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















