package com.kenstarry.houseops_revamped.core.domain.use_cases

import android.location.Location
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.repository.LocationClient
import kotlinx.coroutines.flow.Flow

class GetCurrentLocation(
    private val locationClient: LocationClient
) {
    operator fun invoke(
        interval: Long,
        onResponse: (response: Response<*>) -> Unit
    ): Flow<Location> = locationClient.getLocationUpdates(interval, onResponse)
}