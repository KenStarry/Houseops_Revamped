package com.kenstarry.houseops_revamped.core.domain.model

sealed class Response<out T> {

    //  success status
    data class Success<out T>(val data: T) : Response<T>()

    //  failure status
    data class Failure<out T>(val error: T?) : Response<T>()

}
