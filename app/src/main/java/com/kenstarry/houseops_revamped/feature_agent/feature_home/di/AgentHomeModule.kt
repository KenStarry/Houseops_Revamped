package com.kenstarry.houseops_revamped.feature_agent.feature_home.di

import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.houseops_revamped.feature_agent.feature_home.data.repository.AgentHomeRepositoryImpl
import com.kenstarry.houseops_revamped.feature_agent.feature_home.domain.repository.AgentHomeRepository
import com.kenstarry.houseops_revamped.feature_agent.feature_home.domain.use_cases.AgentHomeUseCases
import com.kenstarry.houseops_revamped.feature_agent.feature_home.domain.use_cases.GetAgentApartments
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AgentHomeModule {

    @Provides
    @Singleton
    fun provideAgentHomeRepository(
        db: FirebaseFirestore
    ) : AgentHomeRepository = AgentHomeRepositoryImpl(db)

    @Provides
    @Singleton
    fun provideAgentHomeUseCases(
        repo: AgentHomeRepository
    ) = AgentHomeUseCases(
        getAgentApartments = GetAgentApartments(repo)
    )
}