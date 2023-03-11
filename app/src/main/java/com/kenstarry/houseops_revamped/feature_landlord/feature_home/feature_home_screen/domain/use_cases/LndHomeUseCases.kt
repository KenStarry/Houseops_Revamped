package com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_home_screen.domain.use_cases

import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_home_screen.domain.use_cases.GetLandlordApartments
import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_home_screen.domain.use_cases.GetLandlordDetails

data class LndHomeUseCases(
    val getLandlordDetails: GetLandlordDetails,
    val getLandlordApartments: GetLandlordApartments
)
