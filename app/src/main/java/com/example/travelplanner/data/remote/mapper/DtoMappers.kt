package com.example.travelplanner.data.remote.mapper

import com.example.travelplanner.data.remote.dto.CountryDto
import com.example.travelplanner.domain.model.DestinationInfo

fun CountryDto.toDomain(): DestinationInfo {
    val currencyEntry = currencies?.values?.firstOrNull()
    val currencyStr = when {
        currencyEntry?.name != null && currencyEntry.symbol != null ->
            "${currencyEntry.name} (${currencyEntry.symbol})"
        currencyEntry?.name != null -> currencyEntry.name
        else -> null
    }
    return DestinationInfo(
        name = name?.common ?: "",
        country = name?.official ?: name?.common ?: "",
        capital = capital?.firstOrNull(),
        region = region,
        currency = currencyStr,
        population = population,
        flagUrl = flags?.png
    )
}
