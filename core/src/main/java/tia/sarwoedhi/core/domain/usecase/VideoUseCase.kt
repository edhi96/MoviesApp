package tia.sarwoedhi.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import tia.sarwoedhi.core.domain.DomainWrapper
import tia.sarwoedhi.core.domain.model.request.VideoParam
import tia.sarwoedhi.core.domain.model.response.VideoEntity
import tia.sarwoedhi.core.domain.repository.movie.MovieRepository
import javax.inject.Inject

class VideoUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(param: VideoParam): Flow<DomainWrapper<List<VideoEntity>>> =
        repository.getListVideoTrailer(param)
}
