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
    private val appDatabase: GameDatabase,
) : LocalDataSource {

    override fun getAllGames(): Flow<List<GameDbDto>> {
        return appDatabase.gameDao().getAllGames()
    }

    override fun getHotGames(): Flow<List<GameDbDto>> {
        return appDatabase.gameDao().getHotGames(getLastMonthDate())
    }

    override fun searchGames(query: String): Flow<List<GameDbDto>> {
        return appDatabase.gameDao().searchGames(query)
    }

    override fun getGameDetail(id: Long): Flow<GameDbDto?> {
        return appDatabase.gameDao().getGameDetails(id)
    }

    override suspend fun setIsFavorites(
        isFavorites: Boolean,
        id: Long,
    ) {
        appDatabase.gameDao().setIsFavorites(isFavorites, id)
    }

    override fun getAllFavoriteGames(): Flow<List<GameDbDto>> {
        return appDatabase.gameDao().getAllFavoriteGames()
    }

    override suspend fun insertGames(games: List<GameDbDto>) {
        appDatabase.gameDao().insertGames(games)
    }

    override suspend fun updateGameDescription(
        id: Long,
        description: String,
    ) {
        appDatabase.gameDao().updateGameDescription(id, description)
    }

    override suspend fun updateGameTrailer(
        id: Long,
        trailerUrl: String,
    ) {
        appDatabase.gameDao().updateGameTrailer(id, trailerUrl)
    }

}