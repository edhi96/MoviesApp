package tia.sarwoedhi.moviesapp.feature.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tia.sarwoedhi.core.domain.model.response.GenreEntity
import tia.sarwoedhi.moviesapp.databinding.FragmentGenreBinding
import tia.sarwoedhi.moviesapp.feature.genre.adapter.GenreAdapter
import tia.sarwoedhi.moviesapp.feature.genre.adapter.OnGenreItemClickCallback

@AndroidEntryPoint
class GenreFragment : Fragment() {

    private var _binding: FragmentGenreBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GenreViewModel by viewModels()
    private var listGenre = mutableListOf<GenreEntity>()
    private lateinit var adapter: GenreAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        onIntent()
        observeViewModel()
    }

    private fun setupUI() {
        adapter = GenreAdapter(listGenre)
        binding.rvGenre.layoutManager = GridLayoutManager(requireContext(), 2)

        adapter.setOnGenreItemClickCallback(object : OnGenreItemClickCallback {
            override fun onItemClicked(data: GenreEntity) {
                nextScreen(data)
            }
        })
        binding.rvGenre.adapter = adapter

    }

    private fun onIntent() {
        viewModel.onIntent(GenreIntent.GetListGenre)
    }

    private fun observeViewModel() {

        viewModel.genreUiState.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { state ->
                binding.tvMessage.text = state.message.ifEmpty { "" }
                binding.tvMessage.visibility = if (state.isError) View.VISIBLE else View.GONE
                binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                if(state.isError){
                    adapter.clear()
                }
                state.listGenre.let {
                    adapter.addData(it)
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun nextScreen(genre: GenreEntity){
        val toSourcesFragment = GenreFragmentDirections.actionGenreFragmentToMovieFragment()
        toSourcesFragment.genre = genre.name
        view?.findNavController()?.navigate(toSourcesFragment)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}