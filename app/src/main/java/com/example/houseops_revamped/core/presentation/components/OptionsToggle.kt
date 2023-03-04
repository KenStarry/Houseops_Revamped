package com.example.houseops_revamped.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseops_revamped.core.domain.model.events.CoreEvents
import com.example.houseops_revamped.core.presentation.model.OptionsToggleModel
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel

@Composable
fun OptionsToggle(
    optionsList: List<OptionsToggleModel>,
    primaryColor: Color,
    tertiaryColor: Color,
    onSelectOption: (option: OptionsToggleModel) -> Unit
) {


    val coreVM: CoreViewModel = hiltViewModel()
    val listState = rememberLazyListState()

    if (coreVM.chosenOptionToggle.value == null) {
        coreVM.onEvent(CoreEvents.ToggleOptions(optionsList.first()))
    }

    LazyRow(
        content = {

            itemsIndexed(optionsList) { index, option ->

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(48.dp))
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .background(
                            color = if (option == coreVM.chosenOptionToggle.value)
                                tertiaryColor
                            else
                                MaterialTheme.colorScheme.onSecondary
                        )
                        .clickable {
                            //  change option type
                            onSelectOption(option)
                            coreVM.onEvent(CoreEvents.ToggleOptions(option))
                        }
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Icon(
                        imageVector = option.icon,
                        contentDescription = "option icon",
                        tint = primaryColor
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(text = option.title)

                }
            }
        },
        state = listState,
        modifier = Modifier
            .clip(RoundedCornerShape(48.dp))
            .wrapContentWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.onSecondary),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    )

}