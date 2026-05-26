package com.example.travelplanner.data.remote.repository

import com.example.travelplanner.data.remote.datasource.RemoteDataSource
import com.example.travelplanner.data.remote.mapper.toDomain
import com.example.travelplanner.domain.model.DestinationInfo
import com.example.travelplanner.domain.repository.DestinationInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DestinationInfoRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : DestinationInfoRepository {

    override suspend fun getDestinationInfo(countryName: String): Result<DestinationInfo> =
        withContext(Dispatchers.IO) {
            runCatching {
                val countries = remoteDataSource.getCountryByName(countryName)
                countries.firstOrNull()?.toDomain()
                    ?: error("Country '$countryName' not found")
            }
        }
}
