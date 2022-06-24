package com.finalexam.podcasts.presentation.player

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.finalexam.podcasts.R
import com.finalexam.podcasts.databinding.FragmentPodcastsPlaylistBinding
import com.finalexam.podcasts.di.Module
import com.finalexam.podcasts.presentation.entity.EpisodePresentationItem
import com.finalexam.podcasts.presentation.player.PodcastsPlayerViewPagerAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PodcastsPlayListFragment : Fragment() {

   private val viewModel: PodcastPlayListViewModel by lazy {
        PodcastPlayListViewModelFactory(Module.repository).create(PodcastPlayListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(FragmentPodcastsPlaylistBinding.bind(view)) {
            viewModel.getPodcastsEpisodes(arguments?.getString("podcastName") ?: "")
            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.episodes.collectLatest {
                        podcastsViewPager.adapter =
                            PodcastsPlayerViewPagerAdapter(it, this@PodcastsPlayListFragment)
                    }
                }
            }
        }
    }

}

