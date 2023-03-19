package com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_home_screen.domain.repository

import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.Landlord
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection

interface LndHomeRepository {

    suspend fun getLandlordDetails(
        email: String,
        landlord: (landlord: UsersCollection) -> Unit,
        response: (response: Response<*>) -> Unit
    )

    suspend fun getLandlordApartments(
        email: String,
        apartments: (apartments: List<Apartment>) -> Unit,
        response: (response: Response<*>) -> Unit
    )
}