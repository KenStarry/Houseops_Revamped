package com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.di

import com.example.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.data.repository.LndAddApartmentRepositoryImpl
import com.example.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.domain.repository.LndAddApartmentRepository
import com.example.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.domain.use_case.AddApartment
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.domain.use_case.AddApartmentUseCases
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AddApartmentModule {

    @Provides
    @Singleton
    fun proivdeLndAddApartmentRepository(
        db: FirebaseFirestore
    ) : LndAddApartmentRepository = LndAddApartmentRepositoryImpl(db)

    @Provides
    @Singleton
    fun provideAddApartmentUseCases(
        repo: LndAddApartmentRepository
    ) = AddApartmentUseCases(
        addApartment = AddApartment(repo)
    )
}