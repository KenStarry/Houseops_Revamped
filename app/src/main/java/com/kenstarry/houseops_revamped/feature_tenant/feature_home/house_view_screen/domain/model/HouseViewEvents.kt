package com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.model

import com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.model.BookedHouseModel

sealed class HouseViewEvents {

    data class AddToBookedHouses(
        val bookedHouse: BookedHouseModel,
        val email: String,
        val isAdd: Boolean
    ) : HouseViewEvents()

    data class AddUserToHouseBooked(
        val apartmentName: String,
        val houseCategory: String,
        val userBooked: UserBooked,
        val isAdd: Boolean
    ) : HouseViewEvents()


}
