package com.example.houseops_revamped.feature_booked.presentation.components.booked_houses

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseops_revamped.feature_booked.domain.model.BookedEvents
import com.example.houseops_revamped.feature_booked.domain.model.BookedHouseModel
import com.example.houseops_revamped.feature_booked.presentation.viewmodel.BookedViewModel
import java.time.LocalDate

@Composable
fun BookedHouses(
    modifier: Modifier = Modifier,
    bookedVm: BookedViewModel,
    bookedHouses: List<BookedHouseModel>
) {

    val listState = rememberLazyListState()
    val houseIds = bookedHouses.map { it.houseId }

    bookedVm.onEvent(
        BookedEvents.GetBookedHouses(
            houseIds = houseIds
        )
    )

    val sortedHouses = bookedHouses.map { LocalDate.parse(it.dateBooked) }
        .sortedBy { it }

    LazyColumn(
        content = {
            items(
                items = bookedHouses
            ) {
                //  bookedItem
                BookedItem(
                    bookedHouse = it,
                    houses = bookedVm.bookedHouses.value
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        },
        state = listState,
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    )


}