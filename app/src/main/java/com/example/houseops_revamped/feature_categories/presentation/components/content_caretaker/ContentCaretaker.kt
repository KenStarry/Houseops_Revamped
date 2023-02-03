package com.example.houseops_revamped.feature_categories.presentation.components.content_caretaker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseops_revamped.core.domain.model.Caretaker
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.feature_categories.domain.model.CategoryEvents
import com.example.houseops_revamped.feature_categories.presentation.viewmodel.CategoriesViewModel

@Composable
fun ContentCaretaker(
    caretakers: List<Caretaker>,
    onCardClicked: (caretaker: Caretaker) -> Unit
) {

    val context = LocalContext.current

    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        content = {

            items(
                items = caretakers
            ) { caretaker ->

                CaretakerCard(
                    context = context,
                    caretaker = caretaker,
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            onCardClicked(caretaker)
                        },
                )

            }
        },
        state = rememberLazyGridState(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    )


}