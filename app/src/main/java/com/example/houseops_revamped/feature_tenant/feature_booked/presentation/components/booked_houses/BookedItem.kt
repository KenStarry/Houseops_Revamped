package com.example.houseops_revamped.feature_tenant.feature_booked.presentation.components.booked_houses

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.feature_tenant.feature_booked.domain.model.BookedHouseModel
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.house_item.HouseItem
import com.example.houseops_revamped.navigation.Direction
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookedItem(
    modifier: Modifier = Modifier,
    context: Context,
    user: UsersCollection,
    bookedHouse: List<BookedHouseModel>,
    houses: List<HouseModel>,
    direction: Direction,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val formattedDate by remember {
        mutableStateOf(
            DateTimeFormatter
                .ofPattern("MMM dd yyyy")
                .format(LocalDate.parse(bookedHouse[0].dateBooked))
        )
    }

    //  if any of the ids maches the ids in the houses queried
    val validHouses = houses.filter { house ->
        bookedHouse.any { bookedHouse ->
            bookedHouse.houseId == house.houseId
        }
    }
    val listState = rememberLazyListState()

    Row(
        modifier = modifier
    ) {

        //  header
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //  ring
            Ring()

            Spacer(modifier = Modifier.height(16.dp))

            Divider(
                modifier = Modifier
                    .width(2.dp)
                    .height(260.dp)
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

            //  date and date remining
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {

                //  date
                Text(
                    text = formattedDate,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )

                //  days remaining
//                Text(
//                    text = formattedDate,
//                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
//                    fontWeight = FontWeight.Bold,
//                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
//                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            //  display all booked houses
            LazyRow(
                content = {
                    items(
                        items = validHouses
                    ) {

                        HouseItem(
                            context = context,
                            house = it,
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .size(
                                    width = 190.dp,
                                    height = 260.dp
                                )
                                .background(MaterialTheme.colorScheme.onSecondary)
                                .clickable {
                                    //  open house view Screen
                                    direction.navigateToHouseView(
                                        it.houseApartmentName, it.houseCategory
                                    )
                                }
                                .padding(8.dp),
                            user = user,
                            snackbarHostState = null,
                            primaryColor = primaryColor,
                            tertiaryColor = tertiaryColor
                        )

                    }
                },
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            )
        }

    }
}























