package com.example.houseops_revamped.feature_home.home_screen.di

import com.example.houseops_revamped.feature_home.home_screen.domain.repository.HomeRepository
import com.example.houseops_revamped.feature_home.home_screen.domain.use_cases.GetHouses
import com.example.houseops_revamped.feature_home.home_screen.domain.use_cases.HomeUseCases
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HouseModule {

    @Provides
    @Singleton
    fun provideHomeRepository(
        db: FirebaseFirestore
    ) : HomeRepository =
        com.example.houseops_revamped.feature_home.home_screen.data.repository.HomeRepositoryImpl(db)

    @Provides
    @Singleton
    fun provideHomeUseCases(
        repo: HomeRepository
    ) = HomeUseCases(
        getHouses = GetHouses(repo)
    )
}



















