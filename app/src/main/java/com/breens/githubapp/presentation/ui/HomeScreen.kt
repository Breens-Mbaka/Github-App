package com.breens.githubapp.presentation.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.breens.githubapp.R
import com.breens.githubapp.databinding.FragmentHomeScreenBinding
import com.breens.githubapp.domain.models.User
import com.breens.githubapp.presentation.adapter.RepositoriesAdapter
import com.breens.githubapp.presentation.viewmodels.GetUserProfileViewModel
import com.breens.githubapp.presentation.viewmodels.GetUsersReposViewModel
import com.breens.githubapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.fragment_home_screen) {

    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!
    private val getUserProfileViewModel: GetUserProfileViewModel by viewModels()
    private val getUserReposViewModel: GetUsersReposViewModel by viewModels()
    private lateinit var repositoriesAdapter: RepositoriesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var pref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        initializePreference()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideActionBar()
        initializeRecyclerview()
        getUserProfileViewModel.searchQuery.value?.let { userProfileResponseObserver(it) }
        searchButtonListener()
        viewUsersFollowers()
        viewUsersFollowing()
    }

    private fun searchButtonListener() {
        binding.searchButton.setOnClickListener {
            val user = searchGithubUserListener()
            storeSearchQuery(user)
            userProfileResponseObserver(user)
        }
    }

    private fun userProfileResponseObserver(searchString: String) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            getUserProfileViewModel.searchForGithubProfile(
                searchString.lowercase(Locale.getDefault()).trim()
            ).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val data = result.data
                        val user = data?.login
                        setUpHomeScreen(data)
                        findUserRepos(user)
                    }

                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        val code = result.code?.toInt()
                        if (code == 0) {
                            Log.d("CODE", code.toString())
                            Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Log.d("CODE", code.toString())
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

    private fun findUserRepos(user: String?) {
        usersReposResponseObserver(user.toString())
    }

    private fun usersReposResponseObserver(searchString: String) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            getUserReposViewModel.searchForGithubUsersRepos(
                searchString.lowercase(Locale.getDefault()).trim()
            ).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val data = result.data
                        repositoriesAdapter.differ.submitList(data)
                    }

                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        val code = result.code?.toInt()
                        if (code == 0) {
                            Log.d("CODE", code.toString())
                            Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Log.d("CODE", code.toString())
                            Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT)
                                .show()
                        }
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun setUpHomeScreen(data: User?) {
        binding.apply {
            avatar.load(data?.avatar_url) {
                crossfade(false)
                transformations(CircleCropTransformation())
            }
            userName.text = data?.login
            "${data?.public_repos} repositories".also { repositoryNumber.text = it }
            repositoryCount.text = data?.public_repos.toString()
            followersCount.text = data?.followers.toString()
            followingCount.text = data?.following.toString()
            bio.text = data?.bio
            location.text = data?.location
        }
    }

    private fun searchGithubUserListener(): String {
        return binding.searchEditText.text.toString()
    }

    private fun initializeRecyclerview() {
        repositoriesAdapter = RepositoriesAdapter()
        recyclerView = binding.repositoriesRecyclerView
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = repositoriesAdapter
    }

    private fun viewUsersFollowers() {
        binding.followersButton.setOnClickListener {
            val name = pref.getString(getString(R.string.searchQuery), null).toString()
            val githubUser = Bundle()
            githubUser.putString("user",name)
            findNavController().navigate(R.id.action_homeScreen_to_followersFragment, githubUser)
        }
    }

    private fun viewUsersFollowing() {
        binding.followingButton.setOnClickListener {
            val githubUser = Bundle()
            val name = pref.getString(getString(R.string.searchQuery), null).toString()
            githubUser.putString("user",name)
            findNavController().navigate(R.id.action_homeScreen_to_followingFragment, githubUser)
        }
    }

    private fun storeSearchQuery(userName: String) {
        with(pref.edit()) {
            putString(getString(R.string.searchQuery), userName)
            apply()
        }
    }

    private fun initializePreference() {
        pref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
    }

    private fun hideActionBar() {
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}