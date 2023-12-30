package com.repleyva.data.repository

import com.repleyva.common.utils.extensions.Range
import com.repleyva.common.utils.extensions.getDateRange
import com.repleyva.common.vo.Resource
import com.repleyva.data.datasource.NetworkBoundResource
import com.repleyva.data.datasource.local.LocalDataSource
import com.repleyva.data.datasource.local.tables.GameDbDto
import com.repleyva.data.datasource.local.tables.mapToDomain
import com.repleyva.data.datasource.remote.model.GameDto
import com.repleyva.data.datasource.remote.model.GameTrailerResponse
import com.repleyva.data.datasource.remote.model.GamesResponse
import com.repleyva.data.datasource.remote.web.ApiService
import com.repleyva.domain.model.GameEntity
import com.repleyva.domain.repository.GamesRepository
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val remoteDataSource: ApiService,
    private val localDataSource: LocalDataSource
) : GamesRepository {

    override fun getAllGames(): Flow<Resource<List<GameEntity>>> =
        object : NetworkBoundResource<List<GameEntity>, GamesResponse>() {
            override fun loadFromDB(): Flow<List<GameEntity>> {
                return localDataSource.getAllGames().mapFlowToDomain()
            }

            override fun shouldFetch(data: List<GameEntity>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): ApiResponse<GamesResponse> =
                remoteDataSource.getAllGames()

            override suspend fun saveCallResult(data: GamesResponse) {
                localDataSource.insertGames(data.results?.map { GameDbDto(it) }.orEmpty())
            }
        }.asFlow()

    override fun getHotGames(): Flow<Resource<List<GameEntity>>> =
        object : NetworkBoundResource<List<GameEntity>, GamesResponse>() {
            override fun loadFromDB(): Flow<List<GameEntity>> {
                return localDataSource.getHotGames().mapFlowToDomain()
            }

            override fun shouldFetch(data: List<GameEntity>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): ApiResponse<GamesResponse> =
                remoteDataSource.getAllGames(
                    dates = getDateRange(range = Range.MONTH, isPast = true),
                    ordering = "-rating",
                    page = 1,
                    pageSize = 10,
                )

            override suspend fun saveCallResult(data: GamesResponse) {
                localDataSource.insertGames(data.results?.map { GameDbDto(it) }.orEmpty())
            }
        }.asFlow()

    override fun getGameDetails(id: Long): Flow<Resource<GameEntity>> =
        object : NetworkBoundResource<GameEntity, GameDto>() {
            override fun loadFromDB(): Flow<GameEntity> {
                return localDataSource.getGameDetail(id).mapToDomain()
            }

            override suspend fun createCall(): ApiResponse<GameDto> =
                remoteDataSource.getGameDetails(id)

            override suspend fun saveCallResult(data: GameDto) {
                localDataSource.updateGameDescription(data.id ?: 0, data.description.orEmpty())
            }

            override fun shouldFetch(data: GameEntity?): Boolean =
                data?.description.isNullOrEmpty()

        }.asFlow()

    override fun getGameTrailer(id: Long): Flow<Resource<GameEntity>> =
        object : NetworkBoundResource<GameEntity, GameTrailerResponse>() {
            override fun loadFromDB(): Flow<GameEntity> {
                return localDataSource.getGameDetail(id).mapToDomain()
            }

            override suspend fun createCall(): ApiResponse<GameTrailerResponse> =
                remoteDataSource.getGameTrailers(id)

            override suspend fun saveCallResult(data: GameTrailerResponse) {
                localDataSource.updateGameTrailer(id, data.results?.firstOrNull()?.data?.x480.orEmpty())
            }

            override fun shouldFetch(data: GameEntity?): Boolean =
                data?.trailerUrl == null

        }.asFlow()

    override fun searchGame(query: String): Flow<Resource<List<GameEntity>>> =
        object : NetworkBoundResource<List<GameEntity>, GamesResponse>() {
            override fun loadFromDB(): Flow<List<GameEntity>> {
                return localDataSource.searchGames(query).mapFlowToDomain()
            }

            override suspend fun createCall(): ApiResponse<GamesResponse> =
                remoteDataSource.getAllGames(search = query)

            override suspend fun saveCallResult(data: GamesResponse) {
                localDataSource.insertGames(data.results?.map { GameDbDto(it) }.orEmpty())
            }

            override fun shouldFetch(data: List<GameEntity>?): Boolean =
                data.isNullOrEmpty()

        }.asFlow()

    override suspend fun setIsFavorites(isFavorites: Boolean, id: Long) {
        localDataSource.setIsFavorites(isFavorites, id)
    }

    override fun getAllFavoritesGames(): Flow<List<GameEntity>> {
        return localDataSource.getAllFavoriteGames().mapFlowToDomain()
    }
}

private fun Flow<List<GameDbDto>>.mapFlowToDomain() = map { gameDbDtoList ->
    gameDbDtoList.map { it.mapToDomain() }
}

private fun Flow<GameDbDto?>.mapToDomain() = map { it.mapToDomain() }