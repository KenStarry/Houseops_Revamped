package com.example.houseops_revamped.feature_home.house_view_screen.presentation.components

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

        HorizontalPager(
            count = house.houseImageUris.size,
            state = rememberPagerState()
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

    }


}















