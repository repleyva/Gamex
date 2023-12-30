package com.repleyva.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameEntity(
    val id: Long,
    val slug: String,
    val name: String,
    val released: String,
    val tba: Boolean,
    val backgroundImage: String,
    val rating: Double,
    val ratingTop: Int,
    val ratingsCount: Int,
    val reviewsTextCount: Int,
    val added: Int,
    val metacritic: Int,
    val playtime: Int,
    val suggestionsCount: Int,
    val updated: String,
    val reviewsCount: Int,
    val saturatedColor: String,
    val dominantColor: String,
    val parentPlatforms: List<String>,
    val genres: List<String>,
    val stores: List<String>,
    val tags: List<String>,
    val esrbRating: String,
    val shortScreenshots: List<String>,
    val isFavorites: Boolean,
    val description: String,
    val trailerUrl: String?,
) : Parcelable