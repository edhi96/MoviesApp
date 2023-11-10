package tia.sarwoedhi.moviesapp.feature.detail_movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tia.sarwoedhi.core.domain.model.response.MovieReviewEntity
import tia.sarwoedhi.moviesapp.R
import tia.sarwoedhi.moviesapp.databinding.ItemReviewBinding

class ReviewAdapter
    : PagingDataAdapter<MovieReviewEntity, ReviewAdapter.ViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
        }

    }
    inner class ViewHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(reviewEntity: MovieReviewEntity) {
            with(itemView) {
                if (reviewEntity.userName.isNotBlank()) {
                    binding.txtAuthor.text = String.format(
                        context.resources.getString(R.string.list_contents),
                        reviewEntity.userName
                    )
                }

                if (reviewEntity.content.isNotBlank()) {
                    binding.txtOverview.text = String.format(
                        context.resources.getString(R.string.list_contents),
                        reviewEntity.content
                    )
                }

                binding.txtScore.text = String.format(
                    context.resources.getString(R.string.detail_vote),
                    (reviewEntity.rating ?: 0.0).toString()
                )

            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieReviewEntity>() {
            override fun areItemsTheSame(oldItem: MovieReviewEntity, newItem: MovieReviewEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MovieReviewEntity, newItem: MovieReviewEntity): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}

