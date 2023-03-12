package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.model

import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.model.ApartmentHouseFeaturesModel

data class FeaturesState(
    val listOfSelectedFeatures: List<ApartmentHouseFeaturesModel> = emptyList()
)
