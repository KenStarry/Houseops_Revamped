package com.kenstarry.houseops_revamped.feature_tenant.feature_booked.presentation.components.booked_houses

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.houseops_revamped.R
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.model.BookedHouseModel
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel
import com.kenstarry.houseops_revamped.core.domain.model.events.CoreEvents
import com.kenstarry.houseops_revamped.core.presentation.components.Lottie
import com.kenstarry.houseops_revamped.feature_tenant.feature_booked.presentation.utils.BookedConstants
import com.kenstarry.houseops_revamped.core.presentation.components.HouseItem
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.navigation.Direction
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookedItem(
    modifier: Modifier = Modifier,
    coreVM: CoreViewModel = hiltViewModel(),
    context: Context,
    user: UsersCollection,
    bookedHousesUnderSelectedDate: List<BookedHouseModel>,
    houses: List<HouseModel>,
    direction: Direction,
    primaryColor: Color,
    tertiaryColor: Color,
    onDeleteDateCategory: () -> Unit
) {

    val formattedDate by remember {
        mutableStateOf(
            DateTimeFormatter
                .ofPattern("MMM dd yyyy")
                .format(LocalDate.parse(bookedHousesUnderSelectedDate[0].dateBooked))
        )
    }

    //  if any of the ids maches the ids in the houses queried
    val validHouses = houses.filter { house ->
        bookedHousesUnderSelectedDate.any { bookedHouse ->
            bookedHouse.houseId == house.houseId
        }
    }
    val listState = rememberLazyListState()
    var dividerHeight by remember {
        mutableStateOf(0.dp)
    }

    var menuExpanded by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = modifier
    ) {

        //  header
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(top = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //  ring
            Ring()

            Spacer(modifier = Modifier.height(16.dp))

            Divider(
                modifier = Modifier
                    .width(2.dp)
                    .height(dividerHeight)
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
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                //  date
                Text(
                    text = formattedDate,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )

                //  icon for more action
                Box(
                    modifier = Modifier
                        .wrapContentSize(),
                    contentAlignment = Alignment.Center
                ) {

                    IconButton(onClick = { menuExpanded = !menuExpanded }) {
                        Icon(
                            imageVector = Icons.Outlined.MoreVert,
                            contentDescription = "More Icon",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
                        )
                    }

                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false },
                        properties = PopupProperties(
                            dismissOnBackPress = true,
                            dismissOnClickOutside = true
                        )
                    ) {

                        BookedConstants.menuOptions.forEach { option ->
                            DropdownMenuItem(
                                text = {
                                    Text(text = option)
                                },
                                onClick = {
                                    //  if its on delete
                                    when (option) {
                                        BookedConstants.DELETE_CATEGORY -> onDeleteDateCategory()
                                    }
                                    menuExpanded = false
                                },
                                colors = MenuDefaults.itemColors(
                                    textColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                                        alpha = 0.8f
                                    ),
                                    disabledTextColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                                        alpha = 0.5f
                                    )
                                )
                            )
                        }

                    }

                }

                //  days remaining
//                Text(
//                    text = formattedDate,
//                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
//                    fontWeight = FontWeight.Bold,
//                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
//                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            AnimatedVisibility(visible = validHouses.isEmpty()) {

                dividerHeight = 180.dp

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Lottie(
                        rawFile = R.raw.search_icon,
                        isPlaying = true,
                        iterations = 1,
                        modifier = Modifier
                            .size(120.dp)
                    )

                    Text(
                        text = "Could not find booked houses.",
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
                    )

                }
            }

            AnimatedVisibility(visible = validHouses.isNotEmpty()) {

                dividerHeight = 260.dp

                //  display all booked houses
                LazyRow(
                    content = {
                        items(
                            items = validHouses
                        ) {

                            //  get apartment details of the house
                            coreVM.onEvent(
                                CoreEvents.GetApartmentDetails(
                                apartmentName = it.houseApartmentName,
                                response = {}
                            ))

                            HouseItem(
                                context = context,
                                house = it,
                                location = coreVM.apartmentDetails.value?.apartmentLocation?.address
                                    ?: "no location",
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
}























