package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.repository

import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel

interface AgentApartmentRepository {

    //  add a house to firestore
    suspend fun addHouseToFirestore(
        apartmentName: String,
        houseModel: HouseModel,
        onResponse: (response: Response<*>) -> Unit
    )

    suspend fun getApartmentHouses(
        apartmentName: String,
        houses: (housesList: List<HouseModel>) -> Unit,
        onResponse: (response: Response<*>) -> Unit
    )
}