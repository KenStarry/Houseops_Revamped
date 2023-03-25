package com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.utils.HomeConstants
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PillSection(
    modifier: Modifier = Modifier,
    onPillClicked: (categoryTitle: String) -> Unit,
    primaryColor: Color,
    tertiaryColor: Color,
) {

    val staggeredGridState = rememberLazyStaggeredGridState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            staggeredGridState.animateScrollToItem(0)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
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
                            onPillClicked(HomeConstants.homePills[index].title)
                        },
                        paddingVertical = 12.dp,
                        paddingHorizontal = 12.dp,
                        primaryColor = primaryColor,
                        tertiaryColor = tertiaryColor
                    )

                }
            },
            state = staggeredGridState,
            modifier = modifier,
            contentPadding = PaddingValues(vertical = 8.dp)
        )
    }
}