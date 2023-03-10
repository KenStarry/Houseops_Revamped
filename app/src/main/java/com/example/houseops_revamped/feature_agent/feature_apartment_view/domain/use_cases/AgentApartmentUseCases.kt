package com.example.houseops_revamped.feature_agent.feature_apartment_view.domain.use_cases

import com.example.houseops_revamped.feature_agent.feature_apartment_view.domain.repository.AgentApartmentRepository

data class AgentApartmentUseCases(
    val getApartmentHouses: GetApartmentHouses,
    val addHouseToFirestore: AddHouseToFirestore
)