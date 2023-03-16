package com.kenstarry.houseops_revamped.feature_tenant.feature_categories.presentation.components.content_apartment

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.presentation.utils.LndHomeConstants
import com.kenstarry.houseops_revamped.core.domain.model.events.CoreEvents
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_home_screen.presentation.components.AlphabetItem

@Composable
fun ContentApartments(
    primaryColor: Color,
    tertiaryColor: Color
) {

    val alphabetListState = rememberLazyListState()
    val coreVM = hiltViewModel<CoreViewModel>()

    coreVM.onEvent(CoreEvents.GetApartments(
        response = {}
    ))

    LazyColumn(
        content = {
            items(
                items = LndHomeConstants.alphabets
            ) {

                val apartmentsFiltered = coreVM.allApartments.value.filter { apartment ->
                    apartment.apartmentName.first() == it
                }.sortedBy { apartment ->
                    apartment.apartmentName
                }

                if (apartmentsFiltered.isNotEmpty()) {
                    //  alphabet item
                    AlphabetItem(
                        alphabet = it.toString(),
                        apartmentList = apartmentsFiltered,
                        primaryColor = primaryColor,
                        tertiaryColor = tertiaryColor
                    )
                }
            }
        },
        state = alphabetListState,
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    )

}