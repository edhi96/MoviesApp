package tia.sarwoedhi.core.data.remote.source.genre

import tia.sarwoedhi.core.data.remote.base.ApiResult
import tia.sarwoedhi.core.data.remote.model.response.genre.ListGenreResponse

interface GenreNetworkSource {
    suspend fun getListGenre(): ApiResult<ListGenreResponse>
}