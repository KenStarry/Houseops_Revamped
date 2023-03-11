package com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.core.domain.model.Caretaker
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel

@Composable
fun CaretakerSection(
    coreVM: CoreViewModel,
    context: Context,
    onCardClicked: (caretaker: Caretaker) -> Unit
) {

    val caretakers: List<Caretaker> = coreVM.getAllCaretakers()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        //  title
        Text(
            text = "Caretaker Spotlight",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.9f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyRow(
            content = {

                items(
                    items = caretakers
                ) { caretaker ->

                    CaretakerItem(
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                  onCardClicked(caretaker)
                            },
                        caretaker = caretaker,
                        context = context
                    )
                }
            },
            state = rememberLazyListState(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        )

    }


}



















