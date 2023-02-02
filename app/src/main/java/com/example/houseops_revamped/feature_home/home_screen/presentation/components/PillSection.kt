package com.example.houseops_revamped.feature_home.home_screen.presentation.components

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.feature_home.home_screen.presentation.utils.HomeConstants

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PillSection(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        //  title
        Text(
            text = "Categories",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.9f)
        )

        Spacer(modifier = Modifier.height(16.dp))

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
                        },
                        paddingVertical = 12.dp,
                        paddingHorizontal = 12.dp
                    )

                }
            },
            state = rememberLazyStaggeredGridState(),
            modifier = modifier,
            contentPadding = PaddingValues(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        )
    }
}