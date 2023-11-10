package tia.sarwoedhi.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import tia.sarwoedhi.core.domain.DomainWrapper
import tia.sarwoedhi.core.domain.model.response.GenreEntity
import tia.sarwoedhi.core.domain.repository.genre.GenreRepository
import javax.inject.Inject


class GenreUseCase @Inject constructor(private val repository: GenreRepository) {

    operator fun invoke(): Flow<DomainWrapper<List<GenreEntity>>> = repository.getListGenre()

}