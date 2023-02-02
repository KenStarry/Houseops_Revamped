package com.example.houseops_revamped.feature_categories.presentation.components.content_caretaker

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseops_revamped.core.domain.model.Caretaker
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel

@Composable
fun ContentCaretaker(
    coreVM: CoreViewModel = hiltViewModel(),
    caretakers: List<Caretaker>
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
                        .size(
                            width = 150.dp,
                            height = 200.dp
                        )
                )

            }
        }
    )


}