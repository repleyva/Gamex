
package com.repleyva.gamexapp.ui.flow.home

import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.repleyva.common.vo.Resource
import com.repleyva.domain.usecase.GameUseCase
import com.repleyva.gamexapp.ui.base.MVIBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gameUseCase: GameUseCase,
) : MVIBaseViewModel<HomeScreenState, HomeScreenEvent>() {

    private var navigator: DestinationsNavigator? = null

    override fun initState(): HomeScreenState = HomeScreenState()

    override fun intentHandler() {
        executeIntent { event ->
            when (event) {
                is HomeScreenEvent.OnInit -> onInit(event.navigator)
                else -> {}
            }
        }
    }

    private fun onInit(navigator: DestinationsNavigator) {
        this.navigator = navigator
        getAllGames()
    }


    private fun getAllGames() = execute(Dispatchers.IO) {
        gameUseCase.getAllGames().collectCommon(viewModelScope) { result ->
            updateUi {
                when (result) {
                    is Resource.Error -> it.copy(isLoading = false, error = result.message)
                    is Resource.Loading -> it.copy(isLoading = true, error = null)
                    is Resource.Success -> it.copy(games = result.data, isLoading = false, error = null)
                }
            }
        }
    }

}