package com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.model

import com.kenstarry.houseops_revamped.core.domain.model.Response

sealed class BookedEvents {

    data class GetBookedHouses(
        val houseIds: List<String>
    ) : BookedEvents()

    data class DeleteBookedHouseCategory(
        val email: String,
        val bookedHousesUnderCategory: List<BookedHouseModel>,
        val onResponse: (repsonse: Response<*>) -> Unit
    ) : BookedEvents()

}
