package com.repleyva.domain.usecase

import com.repleyva.common.vo.Resource
import com.repleyva.domain.helpers.CommonFlow
import com.repleyva.domain.helpers.asCommonFlow
import com.repleyva.domain.model.GameEntity
import com.repleyva.domain.repository.GamesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameInteractor @Inject constructor(
    private val repo: GamesRepository,
) : GameUseCase {

    override fun getAllGames(): CommonFlow<Resource<List<GameEntity>>> {
        return repo.getAllGames().asCommonFlow()
    }

    override fun getHotGames(): CommonFlow<Resource<List<GameEntity>>> {
        return repo.getHotGames().asCommonFlow()
    }

    override suspend fun setIsFavorites(
        isFavorites: Boolean,
        id: Long,
    ) {
        repo.setIsFavorites(isFavorites, id)
    }

    override fun getBookmarkedGames(): CommonFlow<List<GameEntity>> {
        return repo.getAllFavoritesGames().asCommonFlow()
    }

    override fun getGameDetails(id: Long): CommonFlow<Resource<GameEntity>> {
        return repo.getGameDetails(id).asCommonFlow()
    }

    override fun fetchGameTrailer(id: Long): CommonFlow<Resource<GameEntity>> {
        return repo.getGameTrailer(id).asCommonFlow()
    }

    override fun searchGames(query: String): CommonFlow<Resource<List<GameEntity>>> {
        return repo.searchGame(query).asCommonFlow()
    }

}