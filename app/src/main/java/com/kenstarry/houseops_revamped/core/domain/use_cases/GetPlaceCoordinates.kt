package com.kenstarry.houseops_revamped.core.domain.use_cases

import com.google.android.gms.maps.model.LatLng
import com.kenstarry.houseops_revamped.core.domain.model.PlacesAPIResult
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.repository.CoreRepository

class GetPlaceCoordinates(
    private val repo: CoreRepository
) {
    suspend operator fun invoke(
        place: PlacesAPIResult,
        currentLatLong: (currentLatLong: LatLng) -> Unit,
        response: (response: Response<*>) -> Unit
    ) = repo.getPlaceCoordinates(place, currentLatLong, response)
}