package com.kenstarry.houseops_revamped.core.domain.repository

import android.location.Location
import com.kenstarry.houseops_revamped.core.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface LocationClient {

    fun getLocationUpdates(
        interval: Long,
        onResponse: (response: Response<*>) -> Unit
    ): Flow<Location>

    class LocationException(
        message: String
    ) : Exception()
}