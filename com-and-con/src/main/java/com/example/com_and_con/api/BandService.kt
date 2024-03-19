package com.example.com_and_con.api
import com.example.com_and_con.BandCode
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface BandService {
    @GET("rock-bands/all.json")
    suspend fun getBandCodes(): List<BandCode>
}

fun createRetrofitService(): BandService {
    val contentType = "application/json".toMediaType()
    return Retrofit.Builder()
        .baseUrl("https://wherever.ch/hslu/")
        .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(contentType))
        .build()
        .create(BandService::class.java)
}
