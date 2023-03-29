package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.use_cases

data class AgentApartmentUseCases(
    val getApartmentHouses: GetApartmentHouses,
    val addHouseToFirestore: AddHouseToFirestore,
    val updateHouseInFirestore: UpdateHouseInFirestore
)