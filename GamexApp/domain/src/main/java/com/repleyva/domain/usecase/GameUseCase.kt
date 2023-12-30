package com.repleyva.domain.usecase

import com.repleyva.common.vo.Resource
import com.repleyva.domain.helpers.CommonFlow
import com.repleyva.domain.model.GameEntity
import kotlinx.coroutines.flow.Flow

interface GameUseCase {

    fun getAllGames(): CommonFlow<Resource<List<GameEntity>>>

    fun getHotGames(): CommonFlow<Resource<List<GameEntity>>>

    suspend fun setIsFavorites(
        isFavorites: Boolean,
        id: Long,
    )

    fun getBookmarkedGames(): CommonFlow<List<GameEntity>>

    fun getGameDetails(id: Long): CommonFlow<Resource<GameEntity>>

    fun fetchGameTrailer(id: Long): CommonFlow<Resource<GameEntity>>

    fun searchGames(query: String): CommonFlow<Resource<List<GameEntity>>>

}