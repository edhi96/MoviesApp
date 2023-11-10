package tia.sarwoedhi.core.data.remote.base


import retrofit2.Response

abstract class BaseDataSourceImpl {

    protected inline fun <reified T> executeWithResponse(block: () -> Response<T>): ApiResult<T & Any> {
        try {
            val response = block()
            if (!response.isSuccessful) {
                return ApiResult.Error(response.message(), response.errorBody())
            }
            val body = response.body()
            return ApiResult.Success(body)
        } catch (e: Exception) {
            return ApiResult.Error(e.message ?: "", Exception())
        }
    }

}