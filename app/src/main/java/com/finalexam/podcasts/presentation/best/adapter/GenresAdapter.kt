package com.finalexam.podcasts.presentation.best.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.finalexam.podcasts.databinding.ItemGenreBinding
import com.finalexam.podcasts.presentation.entity.GenrePresentationItem

class GenresAdapter :
    ListAdapter<GenrePresentationItem, GenresAdapter.GenreViewHolder>(GenresDiffItem()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GenreViewHolder(
        ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class GenreViewHolder(private val binding: ItemGenreBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GenrePresentationItem) = with(binding) {
            genreName.text = item.name
            root.setOnClickListener {
                item.onClick.invoke()
            }
        }
}

class GenresDiffItem : DiffUtil.ItemCallback<GenrePresentationItem>() {
    override fun areItemsTheSame(
        oldItem: GenrePresentationItem,
        newItem: GenrePresentationItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: GenrePresentationItem,
        newItem: GenrePresentationItem
    ): Boolean {
        return oldItem == newItem
    }
}
}
