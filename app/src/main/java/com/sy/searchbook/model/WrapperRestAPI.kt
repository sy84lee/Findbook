package com.sy.searchbook.model

import com.google.gson.GsonBuilder
import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WrapperRestAPI {
    val restAPI: KakaoAPI by lazy {
        var gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        retrofit.create(KakaoAPI::class.java)
    }
}