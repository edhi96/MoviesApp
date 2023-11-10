package tia.sarwoedhi.core.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tia.sarwoedhi.core.domain.model.request.DiscoverParam
import tia.sarwoedhi.core.domain.model.response.MovieEntity
import tia.sarwoedhi.core.domain.repository.movie.MovieRepository
import javax.inject.Inject

class DiscoverMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    operator fun invoke(param: DiscoverParam): Flow<PagingData<MovieEntity>> = repository.getListDiscoverMovie(param)

}