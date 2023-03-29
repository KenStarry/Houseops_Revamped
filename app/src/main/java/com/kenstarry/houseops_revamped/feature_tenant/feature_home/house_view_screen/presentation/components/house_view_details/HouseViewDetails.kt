package com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.presentation.components.house_view_details

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.model.HouseViewEvents
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.presentation.components.google_map.DetailsGoogleMap
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.presentation.viewmodel.HouseViewVM

@Composable
fun HouseViewDetails(
    context: Context,
    house: HouseModel,
    userDetails: UsersCollection?,
    apartmentDetails: Apartment?,
    onAgentClicked: (agent: UsersCollection) -> Unit,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val spacing = 24.dp
    val houseViewVM: HouseViewVM = hiltViewModel()

    //  number of users that have booked a house
    DetailsUsersBooked(
        house = house,
        primaryColor = primaryColor,
        tertiaryColor = tertiaryColor,
        onClicked = {

        }
    )

    Spacer(modifier = Modifier.height(spacing))

    //  action icons
    DetailActionIcons(
        house = house,
        userDetails = userDetails,
        primaryColor = primaryColor,
        tertiaryColor = tertiaryColor
    )

    Spacer(modifier = Modifier.height(spacing))

    //  title
    DetailsTitle(
        apartmentDetails = apartmentDetails,
        houseCategory = house.houseCategory,
        primaryColor = primaryColor,
        tertiaryColor = tertiaryColor
    )

    Spacer(modifier = Modifier.height(spacing))

    //  google map
    DetailsGoogleMap(
        primaryColor = primaryColor,
        tertiaryColor = tertiaryColor,
        apartment = apartmentDetails
    )

    Spacer(modifier = Modifier.height(spacing))

    //  agent information & call / message icons
    DetailsAgent(
        context = context,
        house = house,
        agentEmail = apartmentDetails?.apartmentAgentAssigned ?: "no email",
        onCardClicked = {
            onAgentClicked(it)
        },
        onPhoneClicked = { phoneNumber ->
            houseViewVM.onEvent(
                HouseViewEvents.MakePhoneCall(
                    context = context,
                    phoneNumber = phoneNumber
                )
            )
        }
    )

    Spacer(modifier = Modifier.height(spacing))

    //  features
    DetailsFeatures(
        features = house.houseFeatures,
        primaryColor = primaryColor,
        tertiaryColor = tertiaryColor,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )

    Spacer(modifier = Modifier.height(spacing))

    //  description
    DetailsDescription(
        description = house.houseDescription,
        primaryColor = primaryColor,
        tertiaryColor = tertiaryColor
    )

}
























