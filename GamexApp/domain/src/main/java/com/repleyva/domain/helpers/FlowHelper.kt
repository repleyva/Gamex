package com.repleyva.domain.helpers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 *  Got my idea for this from stackoverflow post
 * Source:
 * https://stackoverflow.com/questions/64175099/listen-to-kotlin-coroutine-flow-from-ios
 */

/**
 * Must create a (class + function) since extension functions can't be used on iOS with KMM.
 * Otherwise I'd simply create a Flow<T>.collectCommon() function.
 */

fun <T> Flow<T>.asCommonFlow(): CommonFlow<T> = CommonFlow(this)

class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {

    fun collectCommon(
        coroutineScope: CoroutineScope?, // 'viewModelScope' on Android and 'nil' on iOS
        callback: suspend (T) -> Unit, // callback on each emission
    ) = onEach(callback::invoke)
        .launchIn(coroutineScope ?: CoroutineScope(Main))
}










