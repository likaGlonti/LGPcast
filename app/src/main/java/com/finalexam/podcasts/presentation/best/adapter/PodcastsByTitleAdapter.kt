package com.finalexam.podcasts.presentation.best.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.finalexam.podcasts.databinding.PodcastsRecyclerItemBinding
import com.finalexam.podcasts.databinding.TitleItemBinding
import com.finalexam.podcasts.presentation.entity.BasePodcast
import com.finalexam.podcasts.presentation.entity.Podcasts
import com.finalexam.podcasts.presentation.entity.Title

class PodcastsByTitleAdapter :
    ListAdapter<BasePodcast, RecyclerView.ViewHolder>(PodcastsByTitleDiffItem()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TITLE_VIEW_TYPE)
            TitleViewHolder(
                TitleItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        else if (viewType == PODCASTS_VIEW_TYPE)
            PodcastsRecyclerItemViewHolder(
                PodcastsRecyclerItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        else throw Exception("forbidden view type")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TITLE_VIEW_TYPE) {
            (holder as TitleViewHolder).bind(getItem(position) as Title)
        } else {
            (holder as PodcastsRecyclerItemViewHolder).bind(getItem(position) as Podcasts)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Title -> 0
            is Podcasts -> 1
        }
    }

    companion object {
        const val TITLE_VIEW_TYPE = 0
        const val PODCASTS_VIEW_TYPE = 1
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

class PodcastsByTitleDiffItem : DiffUtil.ItemCallback<BasePodcast>() {
    override fun areItemsTheSame(
        oldItem: BasePodcast,
        newItem: BasePodcast
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: BasePodcast,
        newItem: BasePodcast
    ): Boolean {
        return oldItem == newItem
    }
}