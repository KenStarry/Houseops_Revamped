package com.example.houseops_revamped.feature_tenant.feature_bookmark.domain.repository

import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel

interface BookmarksRepository {

    //  get all bookmarks
    suspend fun getBookmarks(
        userEmail: String,
        bookmarks: (List<String>) -> Unit
    )

    suspend fun getBookmarkedHouses(
        bookmarkModelList: List<String>,
        houses: (MutableList<HouseModel>) -> Unit
    )
}