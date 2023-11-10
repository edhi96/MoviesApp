package tia.sarwoedhi.core.data.remote.source.genre

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import tia.sarwoedhi.core.data.remote.api.GenreApi
import tia.sarwoedhi.core.data.remote.base.ApiResult
import tia.sarwoedhi.core.data.remote.base.BaseDataSourceImpl
import tia.sarwoedhi.core.data.remote.model.response.genre.ListGenreResponse
import tia.sarwoedhi.core.di.dispatcher.IoDispatcher
import javax.inject.Inject

class GenreNetworkSourceImpl @Inject constructor(
    private val genreApi: GenreApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseDataSourceImpl(), GenreNetworkSource {

    override suspend fun getListGenre(): ApiResult<ListGenreResponse> {
        return executeWithResponse {
            withContext(dispatcher) {
                genreApi.getListGenre()
            }
        }
    }

}