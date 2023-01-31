package com.example.houseops_revamped.feature_bookmark.domain.use_case

import com.example.houseops_revamped.feature_bookmark.domain.repository.BookmarksRepository
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel

class GetBookmarkedHouses(
    private val repo: BookmarksRepository
) {

    suspend operator fun invoke(
        apartmentName: String,
        houseCategory: String,
        houses: (MutableList<HouseModel>) -> Unit
    ) = repo.getBookmarkedHouses(
        apartmentName = apartmentName,
        houseCategory = houseCategory,
        houses = {
            houses(it)
        }
    )
}