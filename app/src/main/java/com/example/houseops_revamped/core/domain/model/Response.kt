package com.example.houseops_revamped.core.domain.model

sealed class Response {

    object Success : Response()
    object Loading : Response()
    data class Failure(val e: Exception) : Response()

}
