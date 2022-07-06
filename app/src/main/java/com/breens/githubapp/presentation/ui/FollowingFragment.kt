package com.breens.githubapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.breens.githubapp.R
import com.breens.githubapp.databinding.FragmentFollowingBinding
import com.breens.githubapp.presentation.adapter.FollowingAdapter
import com.breens.githubapp.presentation.viewmodels.GetUsersFollowingViewModel
import com.breens.githubapp.util.Resource
import com.breens.githubapp.util.viewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class FollowingFragment : BottomSheetDialogFragment() {

    private val binding by viewBinding(FragmentFollowingBinding::bind)
    private val getUserFollowingViewModel: GetUsersFollowingViewModel by viewModels()
    private val args: FollowingFragmentArgs by navArgs()
    private lateinit var followingAdapter: FollowingAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideActionBar()
        initializeRecyclerview()
        getUserFollowingViewModel.searchQuery.value?.let { usersFollowingResponseObserver(it) }
        findUserFollowing()
    }

    private fun findUserFollowing() {
        val user = args.user
        usersFollowingResponseObserver(user)
    }

    private fun usersFollowingResponseObserver(searchString: String) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            getUserFollowingViewModel.searchForGithubUsersFollowing(
                searchString.lowercase(Locale.getDefault()).trim()
            ).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val data = result.data
                        followingAdapter.differ.submitList(data)
                    }

                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        val code = result.code?.toInt()
                        if (code == 0) {
                            Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun hideActionBar() {
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    private fun initializeRecyclerview() {
        followingAdapter = FollowingAdapter()
        recyclerView = binding.followingRecyclerView
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = followingAdapter
    }

    override fun onStart() {
        super.onStart()
        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }
}