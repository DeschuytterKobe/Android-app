package com.example.hogentderdezitapplicatie.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://v2.jokeapi.dev/joke/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    // TODO (05) Change the ConverterFactory to the MoshiConverterFactory with our Moshi Object
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


interface JokeApiService {


    @GET("Any?type=single")
    fun getProperties():
            Deferred<JokeProperty>

}

object JokeApi {
    val retrofitService: JokeApiService by lazy { retrofit.create(JokeApiService::class.java) }
}