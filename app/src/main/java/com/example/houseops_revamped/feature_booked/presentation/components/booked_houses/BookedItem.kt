package com.example.houseops_revamped.feature_booked.presentation.components.booked_houses

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.domain.use_cases.UserDetails
import com.example.houseops_revamped.feature_booked.domain.model.BookedHouseModel
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel
import com.example.houseops_revamped.feature_home.home_screen.presentation.components.house_item.HouseItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun BookedItem(
    modifier: Modifier = Modifier,
    context: Context,
    user: UsersCollection,
    bookedHouse: BookedHouseModel,
    houses: List<HouseModel>
) {

    val formattedDate by remember {
        mutableStateOf(
            DateTimeFormatter
                .ofPattern("MMM dd yyyy")
                .format(LocalDate.parse(bookedHouse.dateBooked))
        )
    }

    val validHouses = houses.filter { it.houseId == bookedHouse.houseId }
    val listState = rememberLazyListState()

    Row(
        modifier = modifier
    ) {

        //  header
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //  ring
            Ring()

            Spacer(modifier = Modifier.height(8.dp))

            Divider(
                modifier = Modifier
                    .width(2.dp)
                    .height(50.dp)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        //  content
        Column(
            modifier = Modifier
                .weight(10f),
            horizontalAlignment = Alignment.Start
        ) {

            //  date
            Text(
                text = formattedDate,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )

            //  display all booked houses
            LazyRow(
                content = {
                    items(
                        items = validHouses
                    ) {

                        HouseItem(
                            context = context,
                            house = it,
                            user = user,
                            snackbarHostState = null
                        )

                    }
                },
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentPadding = PaddingValues(8.dp)
            )
        }

    }
}























