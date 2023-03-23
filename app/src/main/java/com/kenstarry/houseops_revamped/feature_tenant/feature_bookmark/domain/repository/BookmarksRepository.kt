package com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.domain.repository

import com.kenstarry.houseops_revamped.core.domain.model.HouseModel

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