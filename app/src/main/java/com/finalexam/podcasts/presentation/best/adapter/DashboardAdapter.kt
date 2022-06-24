package com.finalexam.podcasts.presentation.best.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.finalexam.podcasts.databinding.GenresViewholderLayoutBinding
import com.finalexam.podcasts.databinding.PodcastsRecyclerItemBinding
import com.finalexam.podcasts.databinding.TitleItemBinding
import com.finalexam.podcasts.presentation.entity.BaseDashboardItem
import com.finalexam.podcasts.presentation.entity.GenresAdapterItem
import com.finalexam.podcasts.presentation.entity.Podcasts
import com.finalexam.podcasts.presentation.entity.Title

class PodcastsByTitleAdapter :
    ListAdapter<BaseDashboardItem, RecyclerView.ViewHolder>(PodcastsByTitleDiffItem()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TITLE_VIEW_TYPE -> TitleViewHolder(
                TitleItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            PODCASTS_VIEW_TYPE -> PodcastsRecyclerItemViewHolder(
                PodcastsRecyclerItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            GENRES_VIEW_TYPE -> GenresViewHolder(
                GenresViewholderLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw Exception("forbidden view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TITLE_VIEW_TYPE) {
            (holder as TitleViewHolder).bind(getItem(position) as Title)
        } else if (getItemViewType(position) == PODCASTS_VIEW_TYPE) {
            (holder as PodcastsRecyclerItemViewHolder).bind(getItem(position) as Podcasts)
        } else {
            (holder as GenresViewHolder).bind(getItem(position) as GenresAdapterItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Title -> 0
            is Podcasts -> 1
            is GenresAdapterItem -> 2
        }
    }

    companion object {
        const val TITLE_VIEW_TYPE = 0
        const val PODCASTS_VIEW_TYPE = 1
        const val GENRES_VIEW_TYPE = 2
    }

}

class GenresViewHolder(private val binding: GenresViewholderLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(items: GenresAdapterItem) = with(binding) {
        val gridLayoutManager = GridLayoutManager(itemView.context, 2)
        gridLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        genres.layoutManager = gridLayoutManager

        val genresAdapter = GenresAdapter()
        genres.adapter = genresAdapter
        genresAdapter.submitList(items.genres)
    }
}

class TitleViewHolder(private val binding: TitleItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Title) = with(binding) {
        title.text = item.podcastTypeString
    }
}

class PodcastsRecyclerItemViewHolder(private val binding: PodcastsRecyclerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Podcasts) = with(binding) {
        podcasts.layoutManager =
            LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        val adapter = PodcastsAdapter()
        podcasts.adapter = adapter
        adapter.submitList(item.podcasts)
    }
}

class PodcastsByTitleDiffItem : DiffUtil.ItemCallback<BaseDashboardItem>() {
    override fun areItemsTheSame(
        oldItem: BaseDashboardItem,
        newItem: BaseDashboardItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: BaseDashboardItem,
        newItem: BaseDashboardItem
    ): Boolean {
        return oldItem == newItem
    }
}