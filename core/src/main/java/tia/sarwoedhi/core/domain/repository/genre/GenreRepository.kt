package tia.sarwoedhi.core.domain.repository.genre

import kotlinx.coroutines.flow.Flow
import tia.sarwoedhi.core.domain.DomainWrapper
import tia.sarwoedhi.core.domain.model.response.GenreEntity

interface GenreRepository {
    fun getListGenre(): Flow<DomainWrapper<List<GenreEntity>>>
}