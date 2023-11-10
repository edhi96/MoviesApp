package tia.sarwoedhi.moviesapp.feature.movie

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
import tia.sarwoedhi.core.domain.model.request.DiscoverParam
import tia.sarwoedhi.core.domain.usecase.DiscoverMovieUseCase
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieUseCase: DiscoverMovieUseCase) :
    ViewModel() {

    private val _movieState: MutableStateFlow<MovieUiState> = MutableStateFlow(MovieUiState())
    val movieState: StateFlow<MovieUiState>
        get() = _movieState.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MovieUiState()
        )

    fun onIntent(intent: MovieIntent) {
        when (intent) {
            is MovieIntent.GetListMovieByGenre -> {
                getListMovieByGenre(intent.param)
            }

        }
    }

    private fun getListMovieByGenre(param: DiscoverParam) {

        viewModelScope.launch {
            movieUseCase.invoke(param).cachedIn(viewModelScope).collectLatest { result ->
                _movieState.value = _movieState.value.copy(listMovie = result)
            }
        }
    }


}
