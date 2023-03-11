package com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.model

sealed class BookedEvents {

    data class GetBookedHouses(
        val houseIds: List<String>
    ) : BookedEvents()

}
