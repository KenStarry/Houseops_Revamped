package com.example.houseops_revamped.feature_booked.presentation.components.booked_houses

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.feature_booked.domain.model.BookedEvents
import com.example.houseops_revamped.feature_booked.domain.model.BookedHouseModel
import com.example.houseops_revamped.feature_booked.presentation.viewmodel.BookedViewModel
import com.example.houseops_revamped.navigation.Direction
import java.time.LocalDate

@Composable
fun BookedHouses(
    modifier: Modifier = Modifier,
    context: Context,
    user: UsersCollection,
    bookedVm: BookedViewModel = hiltViewModel(),
    bookedHouses: List<BookedHouseModel>,
    direction: Direction
) {

    val listState = rememberLazyListState()
    val houseIds = bookedHouses.map { it.houseId }

    bookedVm.getBookedHouses(houseIds)

    val allBookedHouses = bookedVm.bookedHouses.value
    Log.d("bookedScreen", "all booked houses = ${allBookedHouses.size}\n" +
            "houseIds = $houseIds")

    val sortedHouses = bookedHouses.map { LocalDate.parse(it.dateBooked) }
        .sortedBy { it }

    LazyColumn(
        content = {
            items(
                items = bookedHouses
            ) {
                //  bookedItem
                BookedItem(
                    context = context,
                    user = user,
                    bookedHouse = it,
                    houses = bookedVm.bookedHouses.value,
                    direction = direction
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        },
        state = listState,
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    )


}