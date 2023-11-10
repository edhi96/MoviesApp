package tia.sarwoedhi.core.domain.usecase

import tia.sarwoedhi.core.domain.DomainWrapper
import tia.sarwoedhi.core.domain.model.request.DetailMovieParam
import tia.sarwoedhi.core.domain.model.response.DetailMovieEntity
import tia.sarwoedhi.core.domain.repository.movie.MovieRepository
import javax.inject.Inject

class DetailMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(param: DetailMovieParam): DomainWrapper<DetailMovieEntity> = repository.getDetailMovie(param)

}