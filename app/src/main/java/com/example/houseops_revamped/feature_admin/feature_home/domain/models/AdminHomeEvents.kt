package com.example.houseops_revamped.feature_admin.feature_home.domain.models

import com.example.houseops_revamped.core.domain.model.Response

sealed class AdminHomeEvents {

    data class GetLandlords(
        val response: (response: Response<*>) -> Unit
    ) : AdminHomeEvents()
}
