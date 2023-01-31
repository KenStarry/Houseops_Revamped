package com.example.houseops_revamped.feature_bookmark.domain.repository

import com.example.houseops_revamped.core.domain.model.BookmarkHouse
import com.example.houseops_revamped.core.domain.model.LikedHouse
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel

interface BookmarksRepository {

    //  get all bookmarks
    suspend fun getBookmarks(
        userEmail: String,
        bookmarks: (List<BookmarkHouse>) -> Unit
    )

    suspend fun getBookmarkedHouses(
        bookmarkModelList: List<BookmarkHouse>,
        houses: (MutableList<HouseModel>) -> Unit
    )
}