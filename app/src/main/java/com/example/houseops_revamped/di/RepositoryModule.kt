package com.example.houseops_revamped.di

import com.example.houseops_revamped.feature_authentication.login.data.repository.LoginRepositoryImpl
import com.example.houseops_revamped.feature_authentication.login.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    //  --------------  LOGIN   -----------
    @Provides
    @Singleton
    fun provideLoginRepository(
        auth: FirebaseAuth
    ) : LoginRepository = LoginRepositoryImpl(auth)
}