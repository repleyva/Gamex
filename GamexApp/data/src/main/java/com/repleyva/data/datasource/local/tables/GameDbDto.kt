package com.repleyva.data.datasource.local.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.repleyva.common.utils.converters.ConverterDate
import com.repleyva.common.utils.extensions.toDate
import com.repleyva.common.utils.extensions.toString
import com.repleyva.data.datasource.remote.model.GameDto
import com.repleyva.domain.model.GameEntity
import java.util.Date

@Entity(tableName = "game")
data class GameDbDto(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "slug") val slug: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "released") val released: Date?,
    @ColumnInfo(name = "tba") val tba: Boolean,
    @ColumnInfo(name = "background_image") val backgroundImage: String,
    @ColumnInfo(name = "rating") val rating: Double,
    @ColumnInfo(name = "rating_top") val ratingTop: Int,
    @ColumnInfo(name = "ratings_count") val ratingsCount: Int,
    @ColumnInfo(name = "reviews_text_count") val reviewsTextCount: Int,
    @ColumnInfo(name = "added") val added: Int,
    @ColumnInfo(name = "metacritic") val metacritic: Int,
    @ColumnInfo(name = "playtime") val playtime: Int,
    @ColumnInfo(name = "suggestions_count") val suggestionsCount: Int,
    @ColumnInfo(name = "updated") val updated: String,
    @ColumnInfo(name = "reviews_count") val reviewsCount: Int,
    @ColumnInfo(name = "saturated_color") val saturatedColor: String,
    @ColumnInfo(name = "dominant_color") val dominantColor: String,
    @ColumnInfo(name = "parent_platforms") val parentPlatforms: List<String>,
    @ColumnInfo(name = "genres") val genres: List<String>,
    @ColumnInfo(name = "stores") val stores: List<String>,
    @ColumnInfo(name = "tags") val tags: List<String>,
    @ColumnInfo(name = "esrb_rating") val esrbRating: String,
    @ColumnInfo(name = "short_screenshots") val shortScreenshots: List<String>,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "trailer_url") val trailerUrl: String?,
    @ColumnInfo(name = "is_favorites") var isFavorites: Boolean,
) {
    constructor(data: GameDto?) : this(
        id = data?.id ?: 0,
        slug = data?.slug.orEmpty(),
        name = data?.name.orEmpty(),
        released = data?.released?.toDate(),
        tba = data?.tba ?: false,
        backgroundImage = data?.backgroundImage.orEmpty(),
        rating = data?.rating ?: 0.0,
        ratingTop = data?.ratingTop ?: 0,
        ratingsCount = data?.ratingsCount ?: 0,
        reviewsTextCount = data?.reviewsTextCount ?: 0,
        added = data?.added ?: 0,
        metacritic = data?.metacritic ?: 0,
        playtime = data?.playtime ?: 0,
        suggestionsCount = data?.suggestionsCount ?: 0,
        updated = data?.updated.orEmpty(),
        reviewsCount = data?.reviewsCount ?: 0,
        saturatedColor = data?.saturatedColor.orEmpty(),
        dominantColor = data?.dominantColor.orEmpty(),
        parentPlatforms = data?.parentPlatforms?.map { it.platform?.name.orEmpty() }.orEmpty(),
        genres = data?.genres?.map { it.name.orEmpty() }.orEmpty(),
        stores = data?.stores?.map { it.store?.name.orEmpty() }.orEmpty(),
        tags = data?.tags?.map { it.name.orEmpty() }.orEmpty(),
        esrbRating = data?.esrbRating?.name.orEmpty(),
        shortScreenshots = data?.shortScreenshots?.map { it.image.orEmpty() }.orEmpty(),
        description = "",
        trailerUrl = null,
        isFavorites = false,
    )
}

fun GameDbDto?.mapToDomain() = GameEntity(
    id = this?.id ?: 0,
    slug = this?.slug.orEmpty(),
    name = this?.name.orEmpty(),
    released = this?.released?.toString(ConverterDate.SQL_DATE).orEmpty(),
    tba = this?.tba ?: false,
    backgroundImage = this?.backgroundImage.orEmpty(),
    rating = this?.rating ?: 0.0,
    ratingTop = this?.ratingTop ?: 0,
    ratingsCount = this?.ratingsCount ?: 0,
    reviewsTextCount = this?.reviewsTextCount ?: 0,
    added = this?.added ?: 0,
    metacritic = this?.metacritic ?: 0,
    playtime = this?.playtime ?: 0,
    suggestionsCount = this?.suggestionsCount ?: 0,
    updated = this?.updated.orEmpty(),
    reviewsCount = this?.reviewsCount ?: 0,
    saturatedColor = this?.saturatedColor.orEmpty(),
    dominantColor = this?.dominantColor.orEmpty(),
    parentPlatforms = this?.parentPlatforms.orEmpty(),
    genres = this?.genres.orEmpty(),
    stores = this?.stores.orEmpty(),
    tags = this?.tags.orEmpty(),
    esrbRating = this?.esrbRating.orEmpty(),
    shortScreenshots = this?.shortScreenshots.orEmpty(),
    isFavorites = this?.isFavorites ?: false,
    description = this?.description.orEmpty(),
    trailerUrl = this?.trailerUrl
)

fun GameEntity.mapToDbDto() = GameDbDto(
    id = id,
    slug = slug,
    name = name,
    released = released.toDate(),
    tba = tba,
    backgroundImage = backgroundImage,
    rating = rating,
    ratingTop = ratingTop,
    ratingsCount = ratingsCount,
    reviewsTextCount = reviewsTextCount,
    added = added,
    metacritic = metacritic,
    playtime = playtime,
    suggestionsCount = suggestionsCount,
    updated = updated,
    reviewsCount = reviewsCount,
    saturatedColor = saturatedColor,
    dominantColor = dominantColor,
    parentPlatforms = parentPlatforms,
    genres = genres,
    stores = stores,
    tags = tags,
    esrbRating = esrbRating,
    shortScreenshots = shortScreenshots,
    isFavorites = isFavorites,
    description = description,
    trailerUrl = trailerUrl
)