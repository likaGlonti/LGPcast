package com.finalexam.podcasts.presentation.best

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.finalexam.podcasts.R
import com.finalexam.podcasts.databinding.FragmentBestPodcastsDashboardBinding
import com.finalexam.podcasts.di.Module
import com.finalexam.podcasts.presentation.best.adapter.GenresAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class BestPodcastsDashboardFragment : Fragment(R.layout.fragment_best_podcasts_dashboard) {
    private val viewModel: BestPodcastsDashboardViewModel by lazy {
        BestPodcastsDashboardViewModelFactory(
            Module.repository, requireContext()
        ).create(BestPodcastsDashboardViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(FragmentBestPodcastsDashboardBinding.bind(view)) {
            setUpAdapter()
        }
    }

    private fun FragmentBestPodcastsDashboardBinding.setUpAdapter() {
        val gridLayoutManager = GridLayoutManager(requireActivity(), 2)
        gridLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        genres.layoutManager = gridLayoutManager

        val adapter = GenresAdapter()
        genres.adapter = adapter

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.genres.collectLatest {
                    adapter.submitList(it)
                }
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.onGenreClick.collectLatest {

                }
            }
        }
    }
}