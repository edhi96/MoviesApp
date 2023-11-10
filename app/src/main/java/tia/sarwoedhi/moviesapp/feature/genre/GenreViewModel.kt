package tia.sarwoedhi.moviesapp.feature.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import tia.sarwoedhi.core.domain.DomainWrapper
import tia.sarwoedhi.core.domain.usecase.GenreUseCase
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(private val genreUseCase: GenreUseCase) : ViewModel() {

    private val _genreUiState: MutableStateFlow<GenreUiState> = MutableStateFlow(GenreUiState())
    val genreUiState: StateFlow<GenreUiState>
        get() = _genreUiState.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = GenreUiState()
        )

    fun onIntent(intent: GenreIntent) {
        when (intent) {
            is GenreIntent.GetListGenre -> {
                getListGenreMovies()
            }
        }
    }

    private fun getListGenreMovies() {
        viewModelScope.launch {
            genreUseCase.invoke().onStart {
                _genreUiState.value = _genreUiState.value.copy(
                    isLoading = true,
                )
            }.collect {
                when (it) {

                    is DomainWrapper.Success -> {
                        _genreUiState.value = _genreUiState.value.copy(
                            isLoading = false,
                            listGenre = it.data,
                            isError = false
                        )
                    }

                    is DomainWrapper.Error -> {
                        _genreUiState.value = _genreUiState.value.copy(
                            isLoading = false,
                            message = it.statusResponse ?: "",
                            isError = true,
                        )
                    }

                }
            }
        }
    }


}