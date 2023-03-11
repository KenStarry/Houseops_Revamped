package com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.di

import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.data.repository.AdminLandlordViewRepoImpl
import com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.domain.repository.AdminLandlordViewRepo
import com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.domain.use_case.AdminLandlordViewUseCases
import com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.domain.use_case.GetApartments
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdminLandlordViewModule {

    @Provides
    @Singleton
    fun provideAdminLandlordViewRepository(
        db: FirebaseFirestore
    ) : AdminLandlordViewRepo = AdminLandlordViewRepoImpl(db)

    @Provides
    @Singleton
    fun provideAdminLandlordViewUseCases(
        repo: AdminLandlordViewRepo
    ) = AdminLandlordViewUseCases(
        getApartments = GetApartments(repo)
    )
}





























