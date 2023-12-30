package com.repleyva.gamexapp.ui.base

import androidx.compose.runtime.Stable
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update

abstract class MVIBaseViewModel<S : State, E : Event> : BaseViewModel() {

    private val intents: Channel<E> = Channel(Channel.BUFFERED)

    private val _uiState: MutableStateFlow<S> by lazy { MutableStateFlow(initState()) }
    val uiState get() = _uiState.asStateFlow()

    init { this.intentHandler() }

    abstract fun initState(): S

    abstract fun intentHandler()

    fun eventHandler(intent: E) {
        execute { intents.send(intent) }
    }

    protected fun updateUi(
        handler: suspend (intent: S) -> S,
    ) = execute {
        _uiState.update { handler(it) }
    }

    protected fun executeIntent(
        action: suspend (E) -> Unit
    ) = execute {
        intents.consumeAsFlow().collect { action(it) }
    }

}

interface Event

@Stable
interface State