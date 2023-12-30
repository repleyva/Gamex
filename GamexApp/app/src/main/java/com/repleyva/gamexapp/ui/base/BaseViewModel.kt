package com.repleyva.gamexapp.ui.base

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    open fun onSaveInstanceState() = Bundle()

    open fun onRestoreInstanceState(savedInstanceState: Bundle) {}

    /**
     * Excute
     */

    protected fun execute(
        dispatcher: CoroutineDispatcher = Dispatchers.Main,
        action: suspend () -> Unit
    ) = viewModelScope.launch(dispatcher) { action() }

}