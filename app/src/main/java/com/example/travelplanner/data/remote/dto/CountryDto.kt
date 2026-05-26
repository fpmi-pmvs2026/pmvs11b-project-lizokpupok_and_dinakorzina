package com.example.travelplanner.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CountryDto(
    @SerializedName("name") val name: NameDto?,
    @SerializedName("capital") val capital: List<String>?,
    @SerializedName("region") val region: String?,
    @SerializedName("currencies") val currencies: Map<String, CurrencyDto>?,
    @SerializedName("flags") val flags: FlagsDto?,
    @SerializedName("population") val population: Long?
)

data class NameDto(
    @SerializedName("common") val common: String?,
    @SerializedName("official") val official: String?
)

data class CurrencyDto(
    @SerializedName("name") val name: String?,
    @SerializedName("symbol") val symbol: String?
)

data class FlagsDto(
    @SerializedName("png") val png: String?,
    @SerializedName("svg") val svg: String?
)
