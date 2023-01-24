package com.example.houseops_revamped.feature_home.di

import com.example.houseops_revamped.feature_home.data.repository.HomeRepositoryImpl
import com.example.houseops_revamped.feature_home.domain.repository.HomeRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HouseModule {

    //  repository
    @Provides
    @Singleton
    fun provideHomeRepository(
        db: FirebaseFirestore
    ) : HomeRepository = HomeRepositoryImpl(db)
}