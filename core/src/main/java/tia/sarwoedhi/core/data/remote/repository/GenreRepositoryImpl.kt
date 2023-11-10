package tia.sarwoedhi.core.data.remote.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import tia.sarwoedhi.core.data.mapper.toDomain
import tia.sarwoedhi.core.data.remote.base.ApiResult
import tia.sarwoedhi.core.data.remote.source.genre.GenreNetworkSource
import tia.sarwoedhi.core.di.dispatcher.IoDispatcher
import tia.sarwoedhi.core.domain.DomainWrapper
import tia.sarwoedhi.core.domain.model.response.GenreEntity
import tia.sarwoedhi.core.domain.repository.genre.GenreRepository
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val dataSource: GenreNetworkSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : GenreRepository {
    override fun getListGenre(): Flow<DomainWrapper<List<GenreEntity>>> = flow {
        when (val result = dataSource.getListGenre()) {
            is ApiResult.Success -> {
                result.data?.genres?.let {
                    emit(DomainWrapper.Success(it.toDomain()))
                }
            }

            is ApiResult.Error -> {
                emit(DomainWrapper.Error(result.error))
            }
        }
    }.flowOn(ioDispatcher)
}