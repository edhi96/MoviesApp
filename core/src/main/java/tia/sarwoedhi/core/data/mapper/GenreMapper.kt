package tia.sarwoedhi.core.data.mapper

import tia.sarwoedhi.core.data.remote.model.response.genre.GenreMovie
import tia.sarwoedhi.core.domain.model.response.GenreEntity

fun GenreMovie?.toDomain() = GenreEntity(id = this?.id, name = this?.name.orEmpty())
fun List<GenreMovie>.toDomain() = map(GenreMovie::toDomain)