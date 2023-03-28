package com.kenstarry.houseops_revamped.core.presentation.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BrokenImage
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel
import com.kenstarry.houseops_revamped.core.domain.model.events.CoreEvents
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.house_item.HouseLocation
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.house_item.HousePrice

@Composable
fun HouseItem(
    modifier: Modifier = Modifier,
    context: Context,
    house: HouseModel,
    user: UsersCollection?,
    snackbarHostState: SnackbarHostState?,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val coreVM: CoreViewModel = hiltViewModel()

    var apartmentDetails by remember {
        mutableStateOf<Apartment?>(null)
    }

    //  get apartment details of the house
    coreVM.onEvent(
        CoreEvents.GetApartmentDetails(
        apartmentName = house.houseApartmentName,
        apartmentDetails = {
            apartmentDetails = it
        },
        response = {}
    ))

    val location = apartmentDetails?.apartmentLocation?.address

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        //  Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            contentAlignment = Alignment.Center
        ) {

            if (house.houseImageUris.isNotEmpty()) {
                CoilImage(
                    context = context,
                    imageUri = house.houseImageUris[0].uri.toUri(),
                    placeholder = com.kenstarry.houseops_revamped.R.drawable.houseops_dark_final,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxSize()
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.BrokenImage,
                        contentDescription = "Broken image",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f),
                        modifier = Modifier
                            .fillMaxSize(0.8f)
                    )
                }
            }
        }

        //  content
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {

                //  house category
                Text(
                    text = house.houseCategory,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                //  house location
                HouseLocation(
                    location = location ?: "no location"
                )

            }

            //  house price
            HousePrice(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                houseModel = house,
                user = user,
                snackbarHostState = snackbarHostState,
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor
            )
        }


    }


}