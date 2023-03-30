package com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.apartment_item

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ApartmentItem(
    modifier: Modifier = Modifier,
    apartment: Apartment?,
    primaryColor: Color,
    tertiaryColor: Color
) {

    apartment?.let { aptmt ->

        Card(
            modifier = modifier,
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onSecondary
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                //  header title icon
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    //  apartment first letter
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(35.dp)
                            .background(tertiaryColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = aptmt.apartmentName.first().uppercase().toString(),
                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                        )
                    }

                    //  arrow icon
                    Icon(
                        imageVector = Icons.Outlined.ArrowRight,
                        contentDescription = "Right arrow",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f)
                    )

                    //  apartment landlord name
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        contentAlignment = Alignment.Center
                    ) {

                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .background(MaterialTheme.colorScheme.onSecondary)
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                imageVector = Icons.Outlined.LocationOn,
                                contentDescription = "location",
                                tint = primaryColor,
                                modifier = Modifier
                                    .size(16.dp)
                            )

                            //  location
                            Text(
                                text = aptmt.apartmentLocation?.address ?: "",
                                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                                maxLines = 1,
                                modifier = Modifier
                                    .basicMarquee(
                                        iterations = Int.MAX_VALUE
                                    )
                            )
                        }

                    }
                }

                //  title
                Text(
                    text = aptmt.apartmentName,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )

                //  view apartment button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HomePillBtns(
                        icon = Icons.Outlined.UnfoldMore,
                        title = "View apartment",
                        primaryColor = primaryColor,
                        tertiaryColor = tertiaryColor,
                        containerColor = MaterialTheme.colorScheme.onSecondary,
                        contentColor = primaryColor
                    ) {

                    }
                }

            }

        }
    }
}





































