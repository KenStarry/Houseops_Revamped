package com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.house_view_details

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel

@Composable
fun HouseViewDetails(
    house: HouseModel
) {

    //  action icons
    DetailActionIcons()

    Spacer(modifier = Modifier.height(16.dp))

    //  title
    DetailsTitle(
        apartmentName = house.houseApartmentName,
        houseCategory = house.houseCategory
    )

    //  caretaker information & call / message icons


}