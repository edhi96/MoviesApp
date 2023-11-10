package tia.sarwoedhi.moviesapp.feature.detail_movie


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import tia.sarwoedhi.core.domain.DomainWrapper
import tia.sarwoedhi.core.domain.model.request.DetailMovieParam
import tia.sarwoedhi.core.domain.model.request.ReviewParam
import tia.sarwoedhi.core.domain.usecase.DetailMovieUseCase
import tia.sarwoedhi.core.domain.usecase.ReviewMovieUseCase
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val reviewUseCase: ReviewMovieUseCase,
    private val detailMovieUseCase: DetailMovieUseCase
) :
    ViewModel() {

    private val _detailMovieUiState: MutableStateFlow<DetailMovieUiState> =
        MutableStateFlow(DetailMovieUiState())
    val detailMovieUiState: StateFlow<DetailMovieUiState>
        get() = _detailMovieUiState.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DetailMovieUiState()
        )

    fun onIntent(intent: DetailMovieIntent) {
        when (intent) {
            is DetailMovieIntent.GetListReviewByMovie -> {
                getListReviewByMovie(intent.param)
            }
            is DetailMovieIntent.GetDetailMovie -> {
                getDetailMovie(intent.param)
            }
        }
    }

    private fun getListReviewByMovie(param: ReviewParam) {

        viewModelScope.launch {
            reviewUseCase.invoke(param).cachedIn(viewModelScope)
                .collectLatest {
                    _detailMovieUiState.value = _detailMovieUiState.value.copy(listReview = it)
                }
        }
    }

    private fun getDetailMovie(detailMovieParam: DetailMovieParam) {
        viewModelScope.launch {
            when (val result = detailMovieUseCase.invoke(detailMovieParam)) {
                is DomainWrapper.Success -> {
                    _detailMovieUiState.value =
                        _detailMovieUiState.value.copy(detailMovieEntity = result.data)
                }

                is DomainWrapper.Error -> {
                    _detailMovieUiState.value =
                        _detailMovieUiState.value.copy(message = result.statusResponse)
                }
            }
        }
    }
}