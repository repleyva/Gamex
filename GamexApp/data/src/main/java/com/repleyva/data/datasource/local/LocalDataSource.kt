package com.repleyva.data.datasource.local

import com.repleyva.data.datasource.local.tables.GameDbDto
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getAllGames(): Flow<List<GameDbDto>>

    fun getHotGames(): Flow<List<GameDbDto>>

    fun searchGames(query: String): Flow<List<GameDbDto>>

    fun getGameDetail(id: Long): Flow<GameDbDto?>

    suspend fun setIsFavorites(
        isFavorites: Boolean,
        id: Long,
    )

    fun getAllFavoriteGames(): Flow<List<GameDbDto>>

    suspend fun insertGames(games: List<GameDbDto>)

    suspend fun updateGameDescription(
        id: Long,
        description: String,
    )

    suspend fun updateGameTrailer(
        id: Long,
        trailerUrl: String,
    )

}