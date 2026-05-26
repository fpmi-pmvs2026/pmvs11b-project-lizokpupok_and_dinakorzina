package com.example.travelplanner.data.remote.api

import com.example.travelplanner.data.remote.dto.CountryDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountriesApiService {

    @GET("name/{name}")
    suspend fun getCountryByName(
        @Path("name") name: String,
        @Query("fields") fields: String = "name,capital,region,currencies,flags,population"
    ): List<CountryDto>
}
