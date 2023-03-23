package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.use_cases

import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.repository.AgentApartmentRepository
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel

class GetApartmentHouses(
    private val repository: AgentApartmentRepository
) {
    suspend operator fun invoke(
        apartmentName: String,
        houses: (housesList: List<HouseModel>) -> Unit,
        onResponse: (response: Response<*>) -> Unit
    ) = repository.getApartmentHouses(apartmentName, houses, onResponse)
}