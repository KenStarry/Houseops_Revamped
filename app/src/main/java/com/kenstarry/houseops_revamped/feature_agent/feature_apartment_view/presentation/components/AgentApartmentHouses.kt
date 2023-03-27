package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material.icons.outlined.House
import androidx.compose.material.icons.outlined.Update
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.core.presentation.components.HouseItemAlt
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel
import com.kenstarry.houseops_revamped.core.domain.model.events.CoreEvents
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AgentApartmentHouses(
    context: Context,
    user: UsersCollection?,
    houses: List<HouseModel>,
    primaryColor: Color,
    tertiaryColor: Color,
    onUpdate: (house: HouseModel) -> Unit,
    onDelete: (house: HouseModel) -> Unit
) {

    val listState = rememberLazyListState()
    val coreVM: CoreViewModel = hiltViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        //  action cards
        AgentActionCards(
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor
        )

        //  title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(35.dp)
                    .background(tertiaryColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.House,
                    contentDescription = "Houses",
                    tint = primaryColor
                )
            }

            Text(
                text = "Houses",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
            )

        }

        //  all added houses
        LazyColumn(
            content = {
                items(houses) { house ->

                    //  get apartment details of the house
                    coreVM.onEvent(
                        CoreEvents.GetApartmentDetails(
                        apartmentName = house.houseApartmentName,
                        response = {}
                    ))

                    HouseItemAlt(
                        context = context,
                        house = house,
                        location = coreVM.apartmentDetails.value?.apartmentLocation?.address
                            ?: "no location",
                        user = user,
                        snackbarHostState = null,
                        primaryColor = primaryColor,
                        tertiaryColor = tertiaryColor,
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(MaterialTheme.colorScheme.onSecondary)
                            .clickable {
                            }
                            .padding(8.dp)
                            .animateItemPlacement(),
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    //  update house
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        //  delete icon
                        TextButton(
                            onClick = {
                                onDelete(house)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.onPrimary,
                                contentColor = MaterialTheme.colorScheme.error
                            )
                        ) {

                            Icon(
                                imageVector = Icons.Outlined.DeleteForever,
                                contentDescription = "delete button"
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(text = "Delete")
                        }

                        //  update house icon
                        HomePillBtns(
                            icon = Icons.Outlined.Update,
                            title = "Update",
                            primaryColor = primaryColor,
                            tertiaryColor = tertiaryColor,
                            onClick = { onUpdate(house) }
                        )

                    }

                }
            },
            state = listState,
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        )

    }

}