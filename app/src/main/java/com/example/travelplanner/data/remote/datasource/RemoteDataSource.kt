package com.example.travelplanner.data.remote.datasource

import com.example.travelplanner.data.remote.api.CountriesApiService
import com.example.travelplanner.data.remote.dto.CountryDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    private val api: CountriesApiService by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesApiService::class.java)
    }

    suspend fun getCountryByName(name: String): List<CountryDto> =
        api.getCountryByName(name)
}
