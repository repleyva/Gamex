package com.repleyva.gamexapp.ui.flow.home

import com.repleyva.domain.model.GameEntity
import com.repleyva.gamexapp.ui.base.State

data class HomeScreenState(
    val games: List<GameEntity> = emptyList(),
    val hotGames: List<GameEntity> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
) : State