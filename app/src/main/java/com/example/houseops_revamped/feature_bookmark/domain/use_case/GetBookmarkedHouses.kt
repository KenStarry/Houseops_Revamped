package com.example.houseops_revamped.feature_bookmark.domain.use_case

import android.util.Log
import com.example.houseops_revamped.core.domain.model.BookmarkHouse
import com.example.houseops_revamped.core.domain.model.LikedHouse
import com.example.houseops_revamped.feature_bookmark.domain.repository.BookmarksRepository
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel

class GetBookmarkedHouses(
    private val repo: BookmarksRepository
) {

    suspend operator fun invoke(
        bookmarkModelList: List<String>,
        houses: (MutableList<HouseModel>) -> Unit
    ) = repo.getBookmarkedHouses(
        bookmarkModelList = bookmarkModelList,
        houses = {
            houses(it)
        }
    )
}