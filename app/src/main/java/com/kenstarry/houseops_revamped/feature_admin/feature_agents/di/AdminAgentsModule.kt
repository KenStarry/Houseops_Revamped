package com.kenstarry.houseops_revamped.feature_admin.feature_agents.di

import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.houseops_revamped.feature_admin.feature_agents.data.repository.AdminAgentsRepositoryImpl
import com.kenstarry.houseops_revamped.feature_admin.feature_agents.domain.repository.AdminAgentsRepository
import com.kenstarry.houseops_revamped.feature_admin.feature_agents.domain.use_case.AdminAgentsUseCase
import com.kenstarry.houseops_revamped.feature_admin.feature_agents.domain.use_case.GetAgents
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdminAgentsModule {

    @Provides
    @Singleton
    fun provideAdminAgentsRepository(
        db: FirebaseFirestore
    ): AdminAgentsRepository = AdminAgentsRepositoryImpl(db)

    @Provides
    @Singleton
    fun provideAdminAgentsUseCase(
        repository: AdminAgentsRepository
    ) = AdminAgentsUseCase(
        getAgents = GetAgents(repository)
    )
}