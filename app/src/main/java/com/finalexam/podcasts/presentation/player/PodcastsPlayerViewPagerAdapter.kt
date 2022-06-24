package com.finalexam.podcasts.presentation.player

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.finalexam.podcasts.presentation.entity.EpisodePresentationItem

class PodcastsPlayerViewPagerAdapter(
    private val items: List<EpisodePresentationItem>,
    fa: Fragment
) :
    FragmentStateAdapter(fa) {
    override fun getItemCount() = items.size

    override fun createFragment(position: Int) = PodcastPlayerFragment.newInstance(items[position])
}