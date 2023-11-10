package tia.sarwoedhi.core.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tia.sarwoedhi.core.data.remote.model.response.BaseListResponse
import tia.sarwoedhi.core.data.remote.model.response.detail_movie.DetailMovie
import tia.sarwoedhi.core.data.remote.model.response.movie.DiscoverMovie
import tia.sarwoedhi.core.data.remote.model.response.detail_movie.MovieReview
import tia.sarwoedhi.core.data.remote.model.response.detail_movie.Video

interface MovieApi {

    @GET("discover/movie")
    suspend fun getListDiscoverMovieByGenre(
        @Query("with_genres") genre: String,
        @Query("page") page: Int
    ): Response<BaseListResponse<DiscoverMovie>>

    @GET("movie/{id}/reviews")
    suspend fun getListReviewByMovieId(
        @Path("id") id: Int,
        @Query("page") pageId: Int
    ): Response<BaseListResponse<MovieReview>>

    @GET("movie/{id}/videos")
    suspend fun getListVideoByMovieId(
        @Path("id") id: Int,
    ): Response<BaseListResponse<Video>>

    @GET("movie/{id}")
    suspend fun getDetailMovie(
        @Path("id") id: Int,
    ): Response<DetailMovie>


    //
}