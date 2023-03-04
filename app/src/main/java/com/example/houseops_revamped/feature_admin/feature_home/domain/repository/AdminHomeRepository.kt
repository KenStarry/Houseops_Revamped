package com.example.houseops_revamped.feature_admin.feature_home.domain.repository

import com.example.houseops_revamped.core.domain.model.Response

interface AdminHomeRepository {

    suspend fun getLandlords(
        response: (response: Response<*>) -> Unit
    )
}