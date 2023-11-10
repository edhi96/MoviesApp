package tia.sarwoedhi.moviesapp.feature.movie

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
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tia.sarwoedhi.core.domain.model.request.DiscoverParam
import tia.sarwoedhi.core.domain.model.response.MovieEntity
import tia.sarwoedhi.moviesapp.R
import tia.sarwoedhi.moviesapp.databinding.FragmentMovieBinding
import tia.sarwoedhi.moviesapp.feature.movie.adapter.LoadingStateAdapter
import tia.sarwoedhi.moviesapp.feature.movie.adapter.MovieAdapter
import tia.sarwoedhi.moviesapp.feature.movie.adapter.OnItemClickCallback

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var adapter: MovieAdapter
    private var genreName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpArgument()
        setupUI()
        onIntent()
        observeViewModel()
    }

    private fun setUpArgument() {
        genreName = MovieFragmentArgs.fromBundle(arguments as Bundle).genre
    }

    private fun setupUI() {

        binding.tvLabel.text = String.format(
            requireContext().resources.getString(R.string.movie_with_genre),
            genreName
        )

        adapter = MovieAdapter()
        binding.rvMovie.layoutManager = LinearLayoutManager(requireContext())
        adapter.setOnItemClickCallback(object : OnItemClickCallback {
            override fun onItemClicked(data: MovieEntity) {
                nextScreen(data)
            }
        })
        setUpPaging()
        binding.rvMovie.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoadingStateAdapter {
                adapter.retry()
            },
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )

    }

    private fun onIntent() {
        viewModel.onIntent(MovieIntent.GetListMovieByGenre(DiscoverParam(genreName)))
    }

    private fun observeViewModel() {
        viewModel.movieState.flowWithLifecycle(
            viewLifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        ).onEach { state ->
            state.listMovie.let {
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun nextScreen(movie: MovieEntity) {
       val toSourcesFragment = MovieFragmentDirections.actionMovieFragmentToDetailMovieFragment()
         toSourcesFragment.movieId = movie.id ?: 1
        view?.findNavController()?.navigate(toSourcesFragment)
    }

    private fun setUpPaging() {

        adapter.addLoadStateListener { loadState ->
             binding.progressBar.visibility = if (loadState.refresh is LoadState.Loading) View.VISIBLE else View.GONE
            if (loadState.mediator?.refresh !is LoadState.Loading) {
                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                error?.let {
                    binding.tvMessage.visibility = View.VISIBLE
                    binding.tvMessage.text = it.error.message
                    adapter.submitData(viewLifecycleOwner.lifecycle, PagingData.empty())
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}