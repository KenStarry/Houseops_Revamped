package com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenstarry.houseops_revamped.core.domain.model.HouseCategoryModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.domain.model.BookmarkEvents
import com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.domain.use_case.BookmarksUseCase
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val useCase: BookmarksUseCase
) : ViewModel() {


    private val _categoryVisibility = mutableStateOf(false)
    val categoryVisibility: State<Boolean> = _categoryVisibility

    private val _bookmarks = mutableStateOf<List<String>>(emptyList())
    val bookmarks: State<List<String>> = _bookmarks

    private val _bookmarkedHouses = mutableStateOf<List<HouseModel>>(emptyList())
    val bookmarkedHouses: State<List<HouseModel>> = _bookmarkedHouses

    private val _listOfCategories = mutableStateOf<List<HouseCategoryModel>>(emptyList())
    val listOfCategories: State<List<HouseCategoryModel>> = _listOfCategories

    fun getBookmarks(
        userEmail: String
    ) {

        viewModelScope.launch {
            useCase.getBookmarks(
                email = userEmail,
                bookmarks = {
                    _bookmarks.value = it
                }
            )
        }

    }

    fun getBookmarkedHouses(
        bookmarkModelList: List<String>,
    ) {
        viewModelScope.launch {
            useCase.getBookmarkedHouses(
                bookmarkModelList = bookmarkModelList,
                houses = {
                    _bookmarkedHouses.value = it
                }
            )
        }
    }

    //  events
    fun onEvent(event: BookmarkEvents) {
        when (event) {

            is BookmarkEvents.ToggleCategoryVisibility -> {
                _categoryVisibility.value = event.isVisible
            }

            is BookmarkEvents.FilterCategories -> {
                viewModelScope.launch {

                    val categoryList = ArrayList<HouseCategoryModel>()

                    if (event.bookmarkedHouses.isNotEmpty()) {
                        event.houseCategories.forEach { category ->

                            if (event.bookmarkedHouses.map { it.houseCategory }.contains(category.title)) {
                                categoryList.add(category)
                                Log.d("categories", "Bookmarked houses - ${event.bookmarkedHouses}")
                            }
                        }
                    }

                    _listOfCategories.value = categoryList
                    Log.d("categories", categoryList.size.toString())
                }
            }
        }
    }
}



















