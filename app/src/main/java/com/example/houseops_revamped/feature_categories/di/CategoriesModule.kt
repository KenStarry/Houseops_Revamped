package com.example.houseops_revamped.feature_categories.di

import com.example.houseops_revamped.feature_categories.data.repository.CategoriesRepositoryImpl
import com.example.houseops_revamped.feature_categories.domain.repository.CategoriesRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CategoriesModule {

    @Provides
    @Singleton
    fun provideCategoriesRepository(
        db: FirebaseFirestore
    ): CategoriesRepository = CategoriesRepositoryImpl(db)

}