package com.example.cookingbook

import com.example.cookingbook.network.RecipesApi
import com.example.cookingbook.network.RecipesRepo
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.spoonacular.com/recipes/"

val viewModelsModule = module {
    viewModel { AllRecipesViewModel(get()) }
}

val netModules = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideRestApi(get()) }
    single { provideRecipesRepo(get()) }
}

fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder().build()

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun provideRestApi(retrofit: Retrofit): RecipesApi = retrofit.create(
    RecipesApi::class.java)

fun provideRecipesRepo(api: RecipesApi): RecipesRepo =
    RecipesRepo(api)