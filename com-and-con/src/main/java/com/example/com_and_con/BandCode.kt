package com.example.com_and_con
import kotlinx.serialization.Serializable

@Serializable
data class BandCode(
    val name: String,
    val code: String
)