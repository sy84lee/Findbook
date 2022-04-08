package com.sy.searchbook

import com.sy.searchbook.model.KakaoAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModules {
    private val viewModelModule = module { viewModel { PagingViewModel(get()) }}
    private val repositoryModule = module {
        single { PagingRepository(get()) }
    }

    private val networkModule = module {
        val baseUrl = "https://dapi.kakao.com"

        fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor
                = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient
                = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        fun provideGithubService(okHttpClient: OkHttpClient): KakaoAPI
                = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KakaoAPI::class.java)

        single { provideOkHttpLoggingInterceptor() }
        single { provideOkHttpClient(get()) }
        single { provideGithubService(get()) }
    }

    val modules = listOf(
        repositoryModule,
        viewModelModule,
        networkModule
    )
}