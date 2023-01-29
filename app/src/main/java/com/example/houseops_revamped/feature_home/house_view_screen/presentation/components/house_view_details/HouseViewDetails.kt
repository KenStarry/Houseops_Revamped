package com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.house_view_details

import androidx.compose.runtime.Composable
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel

@Composable
fun HouseViewDetails(
    house: HouseModel
) {

    //  title
    DetailsTitle(
        apartmentName = house.houseApartmentName,
        houseCategory = house.houseCategory
    )

}