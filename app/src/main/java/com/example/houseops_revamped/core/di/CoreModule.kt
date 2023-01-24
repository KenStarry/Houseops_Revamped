package com.example.houseops_revamped.core.di

import com.example.houseops_revamped.core.data.repository.CoreRepositoryImpl
import com.example.houseops_revamped.core.domain.repository.CoreRepository
import com.example.houseops_revamped.core.domain.use_cases.CoreUseCases
import com.example.houseops_revamped.core.domain.use_cases.CurrentUser
import com.example.houseops_revamped.core.domain.use_cases.IsUserLoggedIn
import com.example.houseops_revamped.core.domain.use_cases.UserDetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideCoreRepository(
        db: FirebaseFirestore,
        auth: FirebaseAuth
    ) : CoreRepository = CoreRepositoryImpl(
        db = db,
        auth = auth
    )

    @Provides
    @Singleton
    fun provideCoreUseCase(
        repo: CoreRepository
    ) = CoreUseCases(
        isUserLoggedIn = IsUserLoggedIn(repo),
        currentUser = CurrentUser(repo),
        userDetails = UserDetails(repo)
    )
}









