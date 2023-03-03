package com.example.houseops_revamped.feature_tenant.feature_bookmark.domain.model

import com.example.houseops_revamped.core.domain.model.HouseCategoryModel
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel

sealed class BookmarkEvents {

    data class ToggleCategoryVisibility(
        val isVisible: Boolean
    ) : BookmarkEvents()

    data class FilterCategories(
        val houseCategories: List<HouseCategoryModel>,
        val bookmarkedHouses: List<HouseModel>
    ) : BookmarkEvents()
}
