package com.example.houseops_revamped.feature_bookmark.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.core.domain.model.BookmarkHouse
import com.example.houseops_revamped.core.domain.model.LikedHouse
import com.example.houseops_revamped.feature_bookmark.domain.use_case.BookmarksUseCase
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val useCase: BookmarksUseCase
) : ViewModel() {

    val _bookmarks = mutableStateOf<List<BookmarkHouse>>(emptyList())
    val bookmarks: State<List<BookmarkHouse>> = _bookmarks

    val _bookmarkedHouses = mutableStateOf<List<HouseModel>>(emptyList())
    val bookmarkedHouses: State<List<HouseModel>> = _bookmarkedHouses

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
        bookmarkModelList: List<BookmarkHouse>,
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
}



















