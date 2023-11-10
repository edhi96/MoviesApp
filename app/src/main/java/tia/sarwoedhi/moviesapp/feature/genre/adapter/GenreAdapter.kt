package tia.sarwoedhi.moviesapp.feature.genre.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tia.sarwoedhi.core.domain.model.response.GenreEntity
import tia.sarwoedhi.moviesapp.R
import tia.sarwoedhi.moviesapp.databinding.ItemGenreBinding

class GenreAdapter(private val genreList: MutableList<GenreEntity>) :
    RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    private lateinit var onGenreItemClickCallback: OnGenreItemClickCallback

    fun setOnGenreItemClickCallback(onGenreItemClickCallback: OnGenreItemClickCallback) {
        this.onGenreItemClickCallback = onGenreItemClickCallback
    }

    fun addData(list: List<GenreEntity>) {
        genreList.clear()
        genreList.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        genreList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = genreList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(holder.itemView.context, genreList[position])
        holder.itemView.setOnClickListener {
            onGenreItemClickCallback.onItemClicked(genreList[position])
        }
    }

    inner class ViewHolder(private val binding: ItemGenreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, genreEntity: GenreEntity) {
            with(binding) {
                if (genreEntity.name.isNotBlank()) {
                    this.txtTitle.text = String.format(
                        context.resources.getString(R.string.list_contents),
                        genreEntity.name
                    )
                }
            }
        }
    }

}

