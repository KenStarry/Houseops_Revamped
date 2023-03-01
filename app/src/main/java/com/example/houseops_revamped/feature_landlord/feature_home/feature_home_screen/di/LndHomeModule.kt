package com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.di

import com.example.houseops_revamped.feature_landlord.feature_home.feature_home_screen.data.repository.LndHomeRepositoryImpl
import com.example.houseops_revamped.feature_landlord.feature_home.feature_home_screen.domain.repository.LndHomeRepository
import com.example.houseops_revamped.feature_landlord.feature_home.feature_home_screen.domain.use_cases.GetLandlordApartments
import com.example.houseops_revamped.feature_landlord.feature_home.feature_home_screen.domain.use_cases.GetLandlordDetails
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.domain.use_cases.LndHomeUseCases
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LndHomeModule {

    @Provides
    @Singleton
    fun provideLndHomeRepository(
        db: FirebaseFirestore
    ) : LndHomeRepository = LndHomeRepositoryImpl(db)

    @Provides
    @Singleton
    fun provideLndHomeUseCases(
        repo: LndHomeRepository
    ) = LndHomeUseCases(
        getLandlordDetails = GetLandlordDetails(repo),
        getLandlordApartments = GetLandlordApartments(repo)
    )

}































