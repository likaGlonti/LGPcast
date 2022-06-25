package com.finalexam.podcasts.presentation.player

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import coil.load
import com.finalexam.podcasts.R
import com.finalexam.podcasts.databinding.FragmentPodcastPlayerBinding
import com.finalexam.podcasts.presentation.entity.EpisodePresentationItem


class PodcastPlayerFragment : Fragment(R.layout.fragment_podcast_player) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(FragmentPodcastPlayerBinding.bind(view)) {
            val episode = arguments?.getParcelable<EpisodePresentationItem>("episode")
            podcastImage.load(episode?.image) {
                crossfade(true)
            }
            title.text = episode?.title ?: ""
        }
    }

    companion object {
        fun newInstance(episodePresentationItem: EpisodePresentationItem) =
            PodcastPlayerFragment().apply {
                arguments = bundleOf("episode" to episodePresentationItem)
            }
    }

}