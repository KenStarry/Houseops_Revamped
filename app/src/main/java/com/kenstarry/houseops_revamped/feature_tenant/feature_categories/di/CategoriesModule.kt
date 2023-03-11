package com.kenstarry.houseops_revamped.feature_tenant.feature_categories.di

import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.houseops_revamped.feature_tenant.feature_categories.data.repository.CategoriesRepositoryImpl
import com.kenstarry.houseops_revamped.feature_tenant.feature_categories.domain.repository.CategoriesRepository
import com.kenstarry.houseops_revamped.feature_tenant.feature_categories.domain.use_case.CategoriesUseCases
import com.kenstarry.houseops_revamped.feature_tenant.feature_categories.domain.use_case.GetCaretakerHouses
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

    @Provides
    @Singleton
    fun provideCategoriesUseCase(
        repo: CategoriesRepository
    ) = CategoriesUseCases(
        getCaretakerHouses = GetCaretakerHouses(repo)
    )
}