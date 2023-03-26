package com.kenstarry.houseops_revamped.feature_tenant.feature_booked.presentation.components.booked_houses

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.model.BookedEvents
import com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.model.BookedHouseModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_booked.presentation.viewmodel.BookedViewModel
import com.kenstarry.houseops_revamped.navigation.Direction

@Composable
fun BookedHouses(
    modifier: Modifier = Modifier,
    context: Context,
    user: UsersCollection,
    bookedVm: BookedViewModel = hiltViewModel(),
    bookedHouses: List<BookedHouseModel>,
    direction: Direction,
    primaryColor: Color,
    tertiaryColor: Color,
    onDeleteDateCategory: (bookedHousesUnderCategory: List<BookedHouseModel>) -> Unit
) {

    val listState = rememberLazyListState()
    val houseIds = bookedHouses.map { it.houseId }

    val bookedHouseDates = bookedHouses.map { it.dateBooked }
        .distinct()
        .sortedDescending()

    bookedVm.onEvent(
        BookedEvents.GetBookedHouses(
            houseIds = houseIds
        )
    )

    LazyColumn(
        content = {
            items(
                items = bookedHouseDates
            ) { houseDate ->

                val housesUnderBookedDate = bookedHouses.filter { it.dateBooked == houseDate }

                //  bookedItem
                BookedItem(
                    context = context,
                    user = user,
                    bookedHousesUnderSelectedDate = housesUnderBookedDate,
                    houses = bookedVm.bookedHouses.value,
                    direction = direction,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    onDeleteDateCategory = {
                        onDeleteDateCategory(housesUnderBookedDate)
                    }
                )


                Spacer(modifier = Modifier.height(24.dp))
            }
        },
        state = listState,
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    )


}