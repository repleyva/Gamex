package com.repleyva.data.datasource.local

import com.repleyva.common.utils.extensions.getLastMonthDate
import com.repleyva.data.datasource.local.dao.GameDao
import com.repleyva.data.datasource.local.database.GameDatabase
import com.repleyva.data.datasource.local.tables.GameDbDto
import com.repleyva.data.datasource.local.tables.mapToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val appDatabase: GameDao,
) : LocalDataSource {

    override fun getAllGames(): Flow<List<GameDbDto>> {
        return appDatabase.getAllGames()
    }

    override fun getHotGames(): Flow<List<GameDbDto>> {
        return appDatabase.getHotGames(getLastMonthDate())
    }

    override fun searchGames(query: String): Flow<List<GameDbDto>> {
        return appDatabase.searchGames(query)
    }

    override fun getGameDetail(id: Long): Flow<GameDbDto?> {
        return appDatabase.getGameDetails(id)
    }

    override suspend fun setIsFavorites(
        isFavorites: Boolean,
        id: Long,
    ) {
        appDatabase.setIsFavorites(isFavorites, id)
    }

    override fun getAllFavoriteGames(): Flow<List<GameDbDto>> {
        return appDatabase.getAllFavoriteGames()
    }

    override suspend fun insertGames(games: List<GameDbDto>) {
        appDatabase.insertGames(games)
    }

    override suspend fun updateGameDescription(
        id: Long,
        description: String,
    ) {
        appDatabase.updateGameDescription(id, description)
    }

    override suspend fun updateGameTrailer(
        id: Long,
        trailerUrl: String,
    ) {
        appDatabase.updateGameTrailer(id, trailerUrl)
    }

}