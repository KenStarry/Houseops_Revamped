package com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.di

import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.data.repository.BookmarksRepositoryImpl
import com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.domain.repository.BookmarksRepository
import com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.domain.use_case.BookmarksUseCase
import com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.domain.use_case.GetBookmarkedHouses
import com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.domain.use_case.GetBookmarks
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BookmarksModule {

    @Provides
    @Singleton
    fun provideBookmarksRepository(
        db: FirebaseFirestore
    ) : BookmarksRepository = BookmarksRepositoryImpl(db)

    @Provides
    @Singleton
    fun provideBookmarksUseCase(
        repo: BookmarksRepository
    ) = BookmarksUseCase(
        getBookmarks = GetBookmarks(repo),
        getBookmarkedHouses = GetBookmarkedHouses(repo)
    )
}