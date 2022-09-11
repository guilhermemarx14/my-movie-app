package com.guilhermemarx14.mymovieapp.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreditsResponse (
    val id: Int?,
    val cast: List<CastPerson>?,
    val crew: List<CrewPerson>?
)

@JsonClass(generateAdapter = true)
data class CastPerson(
    val adult: Boolean?,
    val gender: Int?,
    val id: Int?,
    val known_for_department: String?,
    val name: String?,
    val original_name: String?,
    val popularity: Double?,
    val profile_path: String?,
    val cast_id: Int?,
    val character: String?,
    val credit_id: String?,
    val order: Int?
)

@JsonClass(generateAdapter = true)
data class CrewPerson(
    val adult: Boolean?,
    val gender: Int?,
    val id: Int?,
    val known_for_department: String?,
    val name: String?,
    val original_name: String?,
    val popularity: Double?,
    val profile_path: String?,
    val credit_id: String?,
    val department: String?
)