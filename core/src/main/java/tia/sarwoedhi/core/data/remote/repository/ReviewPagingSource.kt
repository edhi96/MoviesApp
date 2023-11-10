package tia.sarwoedhi.core.data.remote.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import tia.sarwoedhi.core.data.remote.base.ApiResult
import tia.sarwoedhi.core.data.remote.model.request.ReviewRequest
import tia.sarwoedhi.core.data.remote.model.response.detail_movie.MovieReview
import tia.sarwoedhi.core.data.remote.source.movie.MovieNetworkSource

class ReviewPagingSource(private val datasource: MovieNetworkSource, private val id: Int) :
    PagingSource<Int, MovieReview>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieReview> {
            return try {
                val position = params.key ?: 1
                when(val response =  datasource.getListReview(request = ReviewRequest(movieId = id, page = position))){
                    is ApiResult.Success -> {
                        val repos = response.data?.results ?: listOf()
                        LoadResult.Page(
                            data = repos,
                            prevKey = if (position == 1) null else position - 1,
                            nextKey = if (repos.isEmpty()) null else position + 1
                        )
                    }
                    is ApiResult.Error -> {
                        LoadResult.Error(Throwable(message = response.error))
                    }
                }
            } catch (exception: Exception) {
                return LoadResult.Error(exception)
            }


    }

    override fun getRefreshKey(state: PagingState<Int, MovieReview>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}