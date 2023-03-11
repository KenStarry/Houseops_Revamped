package com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.di

import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.data.repository.HomeRepositoryImpl
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.domain.repository.HomeRepository
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.domain.use_cases.GetHouses
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.domain.use_cases.HomeUseCases
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
    ): HomeRepository = HomeRepositoryImpl(
        db
    )

    @Provides
    @Singleton
    fun provideHomeUseCases(
        repo: HomeRepository
    ) = HomeUseCases(
        getHouses = GetHouses(repo)
    )
}



















