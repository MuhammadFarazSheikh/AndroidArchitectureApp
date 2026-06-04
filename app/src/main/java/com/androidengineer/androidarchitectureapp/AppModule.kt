package com.androidengineer.androidarchitectureapp

import com.androidengineer.androidarchitectureapp.data.remote.PostsApiService
import com.androidengineer.androidarchitectureapp.data.remote.PostsRemoteDataSource
import com.androidengineer.androidarchitectureapp.data.repository.PostsRepository
import com.androidengineer.androidarchitectureapp.data.repository.PostsRepositoryImpl
import com.androidengineer.androidarchitectureapp.presentation.viewmodels.PostsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val networkModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<PostsApiService> {
        get<Retrofit>().create(PostsApiService::class.java)
    }
}

private val dataModule = module {
    singleOf(::PostsRemoteDataSource)
    single<PostsRepository> { PostsRepositoryImpl(get()) }
}

private val viewModelModule = module {
    factoryOf(::PostsViewModel)
}

val appModule = listOf(
    networkModule,
    dataModule,
    viewModelModule
)