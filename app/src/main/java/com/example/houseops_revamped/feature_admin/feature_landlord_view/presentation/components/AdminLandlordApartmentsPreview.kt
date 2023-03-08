package com.example.houseops_revamped.feature_admin.feature_landlord_view.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.core.domain.model.Apartment
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.feature_landlord.feature_home.feature_home_screen.presentation.components.ApartmentItem
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@Composable
fun AdminLandlordApartmentsPreview(
    apartments: List<Apartment>,
    primaryColor: Color,
    tertiaryColor: Color,
    onAssignClicked: (apartment: Apartment) -> Unit
) {

    val listState = rememberLazyListState()

    LazyColumn(
        content = {
            itemsIndexed(apartments) { index, apartment ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    ApartmentItem(
                        apartment = apartment,
                        primaryColor = primaryColor,
                        tertiaryColor = tertiaryColor
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        TextButton(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.onPrimary,
                                contentColor = primaryColor
                            )
                        ) {

                            Icon(
                                imageVector = Icons.Outlined.UnfoldMore,
                                contentDescription = "Expand Icon",
                                tint = primaryColor
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(text = "Expand")
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        if (apartment.apartmentAgentAssigned.isNullOrBlank()) {

                            //  assign button
                            HomePillBtns(
                                icon = Icons.Outlined.SupportAgent,
                                title = "Assign",
                                primaryColor = primaryColor,
                                tertiaryColor = tertiaryColor,
                                onClick = {
                                    onAssignClicked(apartment)
                                }
                            )

                        } else {

                            TextButton(
                                onClick = { onAssignClicked(apartment) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.onPrimary,
                                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                                        alpha = 0.8f
                                    )
                                )
                            ) {
                                Text(
                                    text = "Assigned to : ${apartment.apartmentAgentAssigned}",
                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                    fontWeight = FontWeight.Normal,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                                )
                            }

                        }
                    }
                }
            }
        },
        state = listState,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .fillMaxSize()
    )

}