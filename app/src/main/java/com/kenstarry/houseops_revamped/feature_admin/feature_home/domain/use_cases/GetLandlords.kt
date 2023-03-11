package com.kenstarry.houseops_revamped.feature_admin.feature_home.domain.use_cases

import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.feature_admin.feature_home.domain.repository.AdminHomeRepository

class GetLandlords(
    private val repo: AdminHomeRepository
) {
    suspend operator fun invoke(
        landlords: (landlords: List<UsersCollection>) -> Unit,
        response: (response: Response<*>) -> Unit
    ) = repo.getLandlords(
        landlords = { landlords(it) },
        response = { response(it) }
    )
}