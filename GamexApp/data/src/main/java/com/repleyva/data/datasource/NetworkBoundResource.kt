package com.repleyva.data.datasource

import com.repleyva.common.vo.Resource
import com.skydoves.sandwich.*
import com.skydoves.sandwich.retrofit.statusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType : Any, RequestType> {

    private var resource: Flow<Resource<ResultType>> = flow {
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Resource.Loading)
            createCall().let {
                it.suspendOnSuccess {
                    saveCallResult(data)
                    emitAll(loadFromDB().map { result ->
                        Resource.Success(result)
                    })
                }.suspendOnError {
                    val error = when (statusCode) {
                        StatusCode.InternalServerError -> "InternalServerError"
                        StatusCode.BadGateway -> "BadGateway"
                        else -> "$statusCode(${statusCode.code}): ${message()}"
                    }
                    emit(Resource.Error(error))
                }.suspendOnException {
                    emit(Resource.Error("Oops, something went wrong!"))
                }
            }
        } else {
            emitAll(loadFromDB().map {
                Resource.Success(it)
            })
        }
    }.onStart { emit(Resource.Loading) }.flowOn(Dispatchers.IO)

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): ApiResponse<RequestType>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = resource
}