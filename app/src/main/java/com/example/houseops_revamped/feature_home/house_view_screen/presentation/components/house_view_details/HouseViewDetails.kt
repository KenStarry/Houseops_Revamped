package com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.house_view_details

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.core.domain.model.Caretaker
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.presentation.utils.intents.phoneCallIntent
import com.example.houseops_revamped.core.presentation.utils.intents.phoneDialIntent
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel

@Composable
fun HouseViewDetails(
    context: Context,
    house: HouseModel,
    userDetails: UsersCollection?,
    onCaretakerClicked: (caretaker: Caretaker) -> Unit,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val spacing = 24.dp

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
        apartmentName = house.houseApartmentName,
        houseCategory = house.houseCategory,
        primaryColor = primaryColor,
        tertiaryColor = tertiaryColor
    )

    Spacer(modifier = Modifier.height(spacing))

    //  caretaker information & call / message icons
    DetailsCaretaker(
        context = context,
        apartment = house.houseApartmentName,
        onCardClicked = {
            onCaretakerClicked(it)
        },
        onPhoneClicked = { phoneNumber ->
            phoneCallIntent(context, phoneNumber)
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
























