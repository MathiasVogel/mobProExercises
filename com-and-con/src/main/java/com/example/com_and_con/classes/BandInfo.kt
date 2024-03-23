package com.example.com_and_con.classes
import kotlinx.serialization.Serializable

@Serializable
data class BandInfo(
    val name: String,
    val members: List<String>,
    val foundingYear: Int,
    val homeCountry: String,
    val bestOfCdCoverImageUrl: String?
)