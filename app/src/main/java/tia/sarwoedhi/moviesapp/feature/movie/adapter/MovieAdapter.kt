package tia.sarwoedhi.moviesapp.feature.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tia.sarwoedhi.core.utils.Constant.posterURL
import tia.sarwoedhi.core.utils.getYear
import tia.sarwoedhi.core.domain.model.response.MovieEntity
import tia.sarwoedhi.moviesapp.R
import tia.sarwoedhi.moviesapp.databinding.ItemMovieBinding

class MovieAdapter
    : PagingDataAdapter<MovieEntity, MovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
            holder.itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(movie)
            }
        }

    }

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(itemView) {
                if (movie.title.isNotBlank()) {
                    binding.txtTitle.text = String.format(
                        context.resources.getString(R.string.list_contents),
                        movie.title
                    )
                }
                if (movie.releaseDate.isNotBlank()) {
                    binding.txtYear.text = String.format(
                        context.resources.getString(R.string.list_year),
                        movie.releaseDate.getYear()
                    )
                }
                if (movie.overview.isNotBlank()) {
                    binding.txtOverview.text = String.format(
                        context.resources.getString(R.string.list_contents),
                        movie.overview
                    )
                }
                binding.txtScore.text = String.format(
                    context.resources.getString(R.string.detail_vote),
                    (movie.voteAverage ?: 0.0).toString()
                )

                binding.labelImgNotAvailable.visibility = if (movie.posterPath.isNotBlank()) View.INVISIBLE else View.GONE
                Glide.with(context)
                    .load(if (movie.posterPath.isNotBlank()) posterURL + movie.posterPath else R.drawable.shape_img_not_available)
                    .into(binding.imgPoster)

            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}

