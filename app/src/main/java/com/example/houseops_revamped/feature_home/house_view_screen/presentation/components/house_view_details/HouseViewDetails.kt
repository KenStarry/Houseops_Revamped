package com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.house_view_details

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel

@Composable
fun HouseViewDetails(
    context: Context,
    house: HouseModel
) {

    val spacing = 24.dp

    //  action icons
    DetailActionIcons(
        price = house.housePrice,
        priceCategory = house.housePriceCategory
    )

    Spacer(modifier = Modifier.height(spacing))

    //  title
    DetailsTitle(
        apartmentName = house.houseApartmentName,
        houseCategory = house.houseCategory
    )

    Spacer(modifier = Modifier.height(spacing))

    //  caretaker information & call / message icons
    DetailsCaretaker(
        context = context,
        apartment = house.houseApartmentName
    )

}