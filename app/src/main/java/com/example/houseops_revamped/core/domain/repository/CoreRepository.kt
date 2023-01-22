package com.example.houseops_revamped.core.domain.repository

interface CoreRepository {

    suspend fun isUserLoggedIn(): Boolean

}