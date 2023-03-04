package com.example.houseops_revamped.feature_landlord.feature_home.feature_home_screen.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.presentation.utils.LndHomeConstants
import com.example.houseops_revamped.feature_landlord.feature_home.feature_home_screen.presentation.viewmodel.LndHomeViewModel

@Composable
fun LndHomeApartments(
    lndHomeVM: LndHomeViewModel,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val alphabetListState = rememberLazyListState()

    LazyColumn(
        content = {
            items(
                items = LndHomeConstants.alphabets
            ) {

                val apartmentsFiltered = lndHomeVM.landlordApartments.value.filter { apartment ->
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
            .fillMaxSize()
    )
}