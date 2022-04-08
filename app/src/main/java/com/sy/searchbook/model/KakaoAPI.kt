package com.sy.searchbook.model

import io.reactivex.Maybe
import retrofit2.Response
import retrofit2.http.*

//It should be hided
const val API_KEY = "ccee0d67b2aad973ee1eb1af04918d51"
const val BASE_URL = "https://dapi.kakao.com"

interface KakaoAPI {
    @GET("/v3/search/book")
    @Headers(
        "Accept:application/json, text/plain, */*",
        "Content-Type:application/json;charset=UTF-8",
        "Authorization: KakaoAK " + API_KEY
    )
    suspend fun getSearchBooks(
        @Query("query") query: String,
        @Query("sort") sort: String? = null,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
        @Query("target") target: String? = null
    ): ResultGetSearchBooks
}