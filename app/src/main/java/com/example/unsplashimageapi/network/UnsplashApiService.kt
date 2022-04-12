package com.example.unsplashimageapi.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/**
 * Location of the Api, it was provided by [Unsplash](https://unsplash.com/)
 */
const val BASE_URL = "https://api.unsplash.com/"

/**
 * My personal access key from Unsplash, I am limited to 50 requests per hour
 */
const val ACCESS_KEY = "GGHMWCsUFRHVRCL5jHePT2dzymk20ixPeV1bkOXKrZI"

/**
 * Build the Moshi object that retrofit wil be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface UnsplashApiService {
    /**
     * Returns a single [UnsplashPhoto] object
     * The @GET annotation indicates the end point
     */
    @GET("search/photos?query=london&per_page=1&client_id=$ACCESS_KEY")
    suspend fun getPhotos(): List<UnsplashPhoto>
}

object UnsplashApi {
    val retrofitService: UnsplashApiService by lazy { retrofit.create(UnsplashApiService::class.java) }
}