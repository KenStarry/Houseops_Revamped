package com.example.houseops_revamped.feature_admin.feature_home.di

import com.example.houseops_revamped.feature_admin.feature_home.data.repository.AdminHomeRepositoryImpl
import com.example.houseops_revamped.feature_admin.feature_home.domain.repository.AdminHomeRepository
import com.example.houseops_revamped.feature_admin.feature_home.domain.use_cases.AdminHomeUseCases
import com.example.houseops_revamped.feature_admin.feature_home.domain.use_cases.GetLandlords
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdminHomeModule {

    @Provides
    @Singleton
    fun provideAdminHomeRepository(
        db: FirebaseFirestore
    ) : AdminHomeRepository = AdminHomeRepositoryImpl(db)

    @Provides
    @Singleton
    fun provideAdminHomeUseCases(
        repo: AdminHomeRepository
    ) = AdminHomeUseCases(
        getLandlords = GetLandlords(repo)
    )

}














