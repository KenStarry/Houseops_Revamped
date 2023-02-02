package com.example.houseops_revamped.feature_home.home_screen.presentation.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.core.domain.model.Caretaker
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel

@Composable
fun CaretakerSection(
    coreVM: CoreViewModel,
    context: Context
) {

    val caretakers: List<Caretaker> = coreVM.getAllCaretakers()

    LazyRow(
        content = {

            items(
                items = caretakers
            ) { caretaker ->

                CaretakerItem(
                    modifier = Modifier
                        .size(
                            width = 150.dp,
                            height = 200.dp
                        ),
                    caretaker = caretaker,
                    context = context
                )
            }
        },
        state = rememberLazyListState(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    )


}



















