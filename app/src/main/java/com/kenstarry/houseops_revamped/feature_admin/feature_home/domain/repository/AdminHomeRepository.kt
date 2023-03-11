package com.kenstarry.houseops_revamped.feature_admin.feature_home.domain.repository

import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection

interface AdminHomeRepository {

    suspend fun getLandlords(
        landlords: (landlords: List<UsersCollection>) -> Unit,
        response: (response: Response<*>) -> Unit
    )
}