package com.example.houseops_revamped.feature_agent.feature_apartment_view.di

import com.example.houseops_revamped.feature_agent.feature_apartment_view.data.repository.AgentApartmentRepositoryImpl
import com.example.houseops_revamped.feature_agent.feature_apartment_view.domain.repository.AgentApartmentRepository
import com.example.houseops_revamped.feature_agent.feature_apartment_view.domain.use_cases.AddHouseToFirestore
import com.example.houseops_revamped.feature_agent.feature_apartment_view.domain.use_cases.AgentApartmentUseCases
import com.example.houseops_revamped.feature_agent.feature_apartment_view.domain.use_cases.GetApartmentHouses
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AgentApartmentModule {

    @Provides
    @Singleton
    fun provideAgentApartmentRepository(
        db: FirebaseFirestore
    ) : AgentApartmentRepository = AgentApartmentRepositoryImpl(db)

    @Provides
    @Singleton
    fun provideAgentApartmentUseCases(
        repository: AgentApartmentRepository
    ) = AgentApartmentUseCases(
        getApartmentHouses = GetApartmentHouses(repository),
        addHouseToFirestore = AddHouseToFirestore(repository)
    )
}
























