package com.finalexam.podcasts.presentation.best

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.finalexam.podcasts.R
import com.finalexam.podcasts.di.Module


class BestPodcastsDashboardFragment : Fragment(R.layout.fragment_best_podcasts_dashboard) {
    private val viewModel: BestPodcastsDashboardViewModel by lazy {
        BestPodcastsDashboardViewModelFactory(
            Module.repository
        ).create(BestPodcastsDashboardViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel
    }

}