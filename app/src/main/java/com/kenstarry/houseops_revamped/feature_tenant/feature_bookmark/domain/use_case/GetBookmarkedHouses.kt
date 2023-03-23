package com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.domain.use_case

import com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.domain.repository.BookmarksRepository
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel

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