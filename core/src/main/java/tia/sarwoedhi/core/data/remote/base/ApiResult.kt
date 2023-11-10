package tia.sarwoedhi.core.data.remote.base

sealed class ApiResult<out R> {
    data class Success<out T>(val data: T?) : ApiResult<T>()
    data class Error(val error: String, val errorBody: Any?) : ApiResult<Nothing>()
}
