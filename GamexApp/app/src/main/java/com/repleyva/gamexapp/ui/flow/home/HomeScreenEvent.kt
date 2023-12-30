package com.repleyva.gamexapp.ui.flow.home

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.repleyva.domain.model.GameEntity
import com.repleyva.gamexapp.ui.base.Event

sealed interface HomeScreenEvent : Event {

    data class OnInit(val navigator: DestinationsNavigator) : HomeScreenEvent

    data class NavigateToDetailScreen(val game: GameEntity) : HomeScreenEvent

}