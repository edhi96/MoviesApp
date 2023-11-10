package tia.sarwoedhi.moviesapp.feature.detail_movie

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tia.sarwoedhi.core.domain.model.request.DetailMovieParam
import tia.sarwoedhi.core.domain.model.request.ReviewParam
import tia.sarwoedhi.core.domain.model.response.DetailMovieEntity
import tia.sarwoedhi.core.utils.Constant
import tia.sarwoedhi.core.utils.getYear
import tia.sarwoedhi.moviesapp.R
import tia.sarwoedhi.moviesapp.databinding.FragmentDetailMovieBinding
import tia.sarwoedhi.moviesapp.feature.detail_movie.adapter.ReviewAdapter

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {

    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var adapter: ReviewAdapter
    private var movieId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpArgument()
        setUpAction()
        onIntent()
        observeViewModel()
    }

    private fun setUpAction() {
        binding.btnBack.setOnClickListener {
            view?.findNavController()?.popBackStack()
        }
    }

    private fun setUpArgument() {
        movieId = DetailMovieFragmentArgs.fromBundle(arguments as Bundle).movieId
    }


    private fun setUpReviewUi() {
        adapter = ReviewAdapter()
        binding.rvReview.layoutManager = LinearLayoutManager(requireContext())
        binding.rvReview.adapter = adapter
        setUpPaging()
    }

    private fun setUpPaging() {
        adapter.addLoadStateListener { loadState ->
            binding.progressBar.visibility =
                if (loadState.refresh is LoadState.Loading) View.VISIBLE else View.GONE
            if (loadState.mediator?.refresh !is LoadState.Loading) {
                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }

                error?.let {
                    binding.progressBar.visibility = View.GONE
                    binding.tvMessage.visibility = View.VISIBLE
                    binding.tvMessage.text = it.error.message
                }
            }
        }
    }

    private fun onIntent() {
        viewModel.onIntent(
            DetailMovieIntent.GetDetailMovie(
                DetailMovieParam(
                    id = movieId ?: 0
                )
            )
        )
        viewModel.onIntent(
            DetailMovieIntent.GetListReviewByMovie(
                ReviewParam(
                    id = movieId ?: 0
                )
            )
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        viewModel.detailMovieUiState.flowWithLifecycle(
            viewLifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        ).onEach { state ->

            setUpUI(state.detailMovieEntity)

            state.listReview.let {
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
                binding.informationReview.visibility =
                    if (adapter.snapshot().items.isEmpty()) View.GONE else View.VISIBLE
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun setUpUI(movieEntity: DetailMovieEntity?) {
        if (movieEntity != null) {
            //load image
            Glide.with(requireContext())
                .load(if (movieEntity.posterPath?.isNotBlank() == true) Constant.posterURL + movieEntity.posterPath else R.drawable.shape_img_not_available)
                .into(binding.imgPoster)

            //image label
            binding.lblImgNotAvailable.visibility =
                if (movieEntity.posterPath?.isNotBlank() == true) View.INVISIBLE else View.GONE

            //title
            binding.txtTitle.text = movieEntity.name

            //genre
            binding.txtGenre.text = String.format(
                requireContext().resources.getString(R.string.detail_genre),
                movieEntity.listGenre?.toString()
            )

            //year

            binding.txtYear.text = String.format(
                requireContext().resources.getString(R.string.list_year),
                if (movieEntity.releaseDate?.isNotBlank() == true) movieEntity.releaseDate?.getYear() else ""
            )


            //rating
            binding.txtScore.text = String.format(
                requireContext().resources.getString(R.string.detail_vote),
                (movieEntity.voteAverage ?: 0.0).toString()
            )

            //overView
            binding.txtOverview.text = String.format(
                requireContext().resources.getString(R.string.list_contents),
                movieEntity.overView
            )
            setUpReviewUi()
        }

    }

}