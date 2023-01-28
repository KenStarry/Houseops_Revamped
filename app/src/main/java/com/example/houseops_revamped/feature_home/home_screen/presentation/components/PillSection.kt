package com.example.houseops_revamped.feature_home.home_screen.presentation.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.feature_home.home_screen.presentation.utils.HomeConstants

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PillSection(
    modifier: Modifier = Modifier
) {

    LazyHorizontalStaggeredGrid(
        rows = StaggeredGridCells.Fixed(2),
        content = {
            itemsIndexed(
                HomeConstants.homePills
            ) { index, pill ->

                HomePillBtns(
                    icon = pill.icon,
                    title = pill.title,
                    onClick = {
                        //  pass the category at the specific index
                        Log.d("category", HomeConstants.homePills[index].title)
                    }
                )

            }
        },
        state = rememberLazyStaggeredGridState(),
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    )
}