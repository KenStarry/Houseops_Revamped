package com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model

import com.kenstarry.houseops_revamped.core.domain.model.HouseModel

data class ApartmentModel(
    val apartmentName: String,
    val houseModel: List<HouseModel>?
) {

    constructor() : this("", emptyList())
}
