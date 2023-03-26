package com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.use_case

import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.model.BookedHouseModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.repository.BookedRepository

class DeleteBookedHouseCategory(
    private val repo: BookedRepository
) {
    suspend operator fun invoke(
        email: String,
        bookedHousesUnderCategory: List<BookedHouseModel>,
        onResponse: (repsonse: Response<*>) -> Unit
    ) = repo.deleteBookedHouseCategory(email, bookedHousesUnderCategory, onResponse)
}