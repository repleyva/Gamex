package com.repleyva.domain.repository

import com.repleyva.common.vo.Resource
import com.repleyva.domain.model.GameEntity
import kotlinx.coroutines.flow.Flow

interface GamesRepository {

    fun getAllGames(): Flow<Resource<List<GameEntity>>>

    fun getHotGames(): Flow<Resource<List<GameEntity>>>

    fun getGameDetails(id: Long): Flow<Resource<GameEntity>>

    fun getGameTrailer(id: Long): Flow<Resource<GameEntity>>

    fun searchGame(query: String): Flow<Resource<List<GameEntity>>>

    suspend fun setIsFavorites(
        isFavorites: Boolean,
        id: Long,
    )

    fun getAllFavoritesGames(): Flow<List<GameEntity>>
}
