package com.finalexam.podcasts.presentation.best.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.finalexam.podcasts.databinding.PodcastChildItemBinding
import com.finalexam.podcasts.presentation.entity.PodcastPresentationItem

class PodcastsAdapter :
    ListAdapter<PodcastPresentationItem, PodcastsViewHolder>(PodcastsDiffItem()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PodcastsViewHolder {
        return PodcastsViewHolder(
            PodcastChildItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PodcastsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class PodcastsViewHolder(private val binding: PodcastChildItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: PodcastPresentationItem) = with(binding) {
        podcastImage.load(item.image) {
            crossfade(true)
        }
        title.text = item.titleOriginal
    }
}

class PodcastsDiffItem : DiffUtil.ItemCallback<PodcastPresentationItem>() {
    override fun areItemsTheSame(
        oldItem: PodcastPresentationItem,
        newItem: PodcastPresentationItem
    ) = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: PodcastPresentationItem,
        newItem: PodcastPresentationItem
    ) = oldItem == newItem

}