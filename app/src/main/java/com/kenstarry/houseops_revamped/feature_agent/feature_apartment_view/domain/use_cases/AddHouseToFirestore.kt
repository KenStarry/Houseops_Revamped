package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.use_cases

import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.repository.AgentApartmentRepository
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel

class AddHouseToFirestore(
    private val repository: AgentApartmentRepository
) {
    suspend operator fun invoke(
        apartmentName: String,
        houseModel: HouseModel,
        onResponse: (response: Response<*>) -> Unit
    ) = repository.addHouseToFirestore(apartmentName, houseModel, onResponse)
}