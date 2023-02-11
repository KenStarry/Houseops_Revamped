package com.example.houseops_revamped.feature_booked.di

import com.example.houseops_revamped.feature_booked.data.repository.BookedRepositoryImpl
import com.example.houseops_revamped.feature_booked.domain.repository.BookedRepository
import com.example.houseops_revamped.feature_booked.domain.use_case.BookedUseCases
import com.example.houseops_revamped.feature_booked.domain.use_case.GetBookedHouses
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BookedModule {

    @Provides
    @Singleton
    fun provideBookedRepository(
        db: FirebaseFirestore
    ) : BookedRepository = BookedRepositoryImpl(db)

    @Provides
    @Singleton
    fun provideBookedUseCases(
        repository: BookedRepository
    ) = BookedUseCases(
        getBookedHouses = GetBookedHouses(repository)
    )

}









































