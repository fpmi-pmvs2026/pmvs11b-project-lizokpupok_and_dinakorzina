package com.example.travelplanner.domain.repository

import com.example.travelplanner.domain.model.DestinationInfo

interface DestinationInfoRepository {
    suspend fun getDestinationInfo(countryName: String): Result<DestinationInfo>
}
