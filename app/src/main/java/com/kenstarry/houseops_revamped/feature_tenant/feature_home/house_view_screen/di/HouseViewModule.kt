package com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.di

import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.data.repository.HouseViewRepositoryImpl
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.repository.HouseViewRepository
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.use_case.AddToBooked
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.use_case.AddUserToHouseBooked
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.use_case.GetHouse
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.use_case.HouseViewUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HouseViewModule {

    @Provides
    @Singleton
    fun provideHouseViewRepository(
        db: FirebaseFirestore
    ): HouseViewRepository = HouseViewRepositoryImpl(db)

    @Provides
    @Singleton
    fun provideHouseViewUseCase(
        repository: HouseViewRepository
    ) = HouseViewUseCases(
        getHouse = GetHouse(repository),
        addToBooked = AddToBooked(repository),
        addUserToHouseBooked = AddUserToHouseBooked(repository)
    )
}