package com.finalexam.podcasts.presentation.best

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.finalexam.podcasts.R
import com.finalexam.podcasts.databinding.FragmentBestPodcastsDashboardBinding
import com.finalexam.podcasts.di.Module
import com.finalexam.podcasts.presentation.best.adapter.PodcastsByTitleAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*


class BestPodcastsDashboardFragment : Fragment(R.layout.fragment_best_podcasts_dashboard) {
    private val viewModel: BestPodcastsDashboardViewModel by lazy {
        BestPodcastsDashboardViewModelFactory(
            Module.repository, requireContext()
        ).create(BestPodcastsDashboardViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(FragmentBestPodcastsDashboardBinding.bind(view)) {
            greeting.text = getGreetingText()
            setUpAdapter()
        }
    }

    private fun FragmentBestPodcastsDashboardBinding.setUpAdapter() {

        val podcastsByTitleAdapter = PodcastsByTitleAdapter()
        podcasts.layoutManager = LinearLayoutManager(requireContext())
        podcasts.adapter = podcastsByTitleAdapter

        lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.dashboardPodcasts.collectLatest {
                    podcastsByTitleAdapter.submitList(it)
                }
            }
        }
        observeViewModel()
    }

    private fun getGreetingText(): String {
        val c: Calendar = Calendar.getInstance()
        return when (c.get(Calendar.HOUR_OF_DAY)) {
            in 12..15 -> "Good Afternoon"
            in 16..20 -> "Good Evening"
            in 21..23 -> "Good Night"
            else -> "Hello"
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.onPodcastClick.collectLatest {
                    val action =
                        BestPodcastsDashboardFragmentDirections.actionBestPodcastsDashboardFragmentToPodcastsPlayListFragment(
                            it
                        )
                    findNavController().navigate(action)
                }
            }
        }
        viewModel.workInfo.observe(viewLifecycleOwner) { workInfo ->
            if (workInfo == null)
                return@observe
            if (workInfo.state.isFinished)
                viewModel.getData()
        }
    }
}