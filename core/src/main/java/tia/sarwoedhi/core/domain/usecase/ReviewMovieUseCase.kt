package tia.sarwoedhi.core.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tia.sarwoedhi.core.domain.model.request.ReviewParam
import tia.sarwoedhi.core.domain.model.response.MovieReviewEntity
import tia.sarwoedhi.core.domain.repository.movie.MovieRepository
import javax.inject.Inject

class ReviewMovieUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(param: ReviewParam): Flow<PagingData<MovieReviewEntity>> =
        repository.getListReviewMovie(param)
}