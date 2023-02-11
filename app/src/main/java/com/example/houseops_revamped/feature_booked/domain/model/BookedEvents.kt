package com.example.houseops_revamped.feature_booked.domain.model

sealed class BookedEvents {

    data class GetBookedHouses(
        val houseIds: List<String>
    ) : BookedEvents()

}
