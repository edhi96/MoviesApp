package tia.sarwoedhi.core.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import tia.sarwoedhi.core.data.remote.model.response.genre.ListGenreResponse

interface GenreApi {

    @GET("genre/movie/list")
    suspend fun getListGenre(): Response<ListGenreResponse>

}