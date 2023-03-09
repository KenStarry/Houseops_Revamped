package com.example.houseops_revamped.feature_agent.feature_home.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.core.domain.model.Apartment

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AgentHomeApartments(
    apartments: List<Apartment>,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val staggeredGridState = rememberLazyStaggeredGridState()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        content = {
            items(apartments) { apartment ->

                //  apartment Item
                AgentHomeApartmentItem(
                    apartment = apartment,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    modifier = Modifier
                        .wrapContentSize()
                )
            }
        },
        state = staggeredGridState,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    )

}