package com.breens.githubapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.breens.githubapp.R
import com.breens.githubapp.databinding.FragmentRepositoriesBinding
import com.breens.githubapp.presentation.adapter.RepositoriesAdapter
import com.breens.githubapp.presentation.viewmodels.GetUsersReposViewModel
import com.breens.githubapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoriesScreen : Fragment(R.layout.fragment_repositories) {

    private var _binding: FragmentRepositoriesBinding? = null
    private val binding get() = _binding!!
    private val getUsersRepositoryViewModel: GetUsersReposViewModel by viewModels()
    private lateinit var repositoriesAdapter: RepositoriesAdapter
    private lateinit var recyclerView: RecyclerView
    private val args: RepositoriesScreenArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoriesBinding.inflate(inflater, container, false)
        initializeRecyclerview()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRepositories()
    }

    private fun getRepositories() {
        val user = args.user
        userRepositoriesResponseObserver(user)
    }

    private fun userRepositoriesResponseObserver(searchString: String) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            getUsersRepositoryViewModel.searchForGithubUsersRepos(searchString.trim())
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            val response = result.data
                            repositoriesAdapter.differ.submitList(response)
                        }

                        is Resource.Error -> {
                            Toast.makeText(
                                requireContext(),
                                result.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is Resource.Loading -> {
                            Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}