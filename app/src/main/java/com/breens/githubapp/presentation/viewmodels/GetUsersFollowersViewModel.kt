package com.breens.githubapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.breens.githubapp.domain.models.Followers
import com.breens.githubapp.domain.usecases.GetUsersFollowersUseCase
import com.breens.githubapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GetUsersFollowersViewModel @Inject constructor(
    private val getUsersFollowersUseCase: GetUsersFollowersUseCase
) : ViewModel() {

    private val _searchQuery = MutableLiveData("John")
    val searchQuery: LiveData<String?> = _searchQuery

    fun searchForGithubUserFollowers(userName: String): Flow<Resource<List<Followers>>> {
        _searchQuery.value = userName
        return getUsersFollowersUseCase.invoke(userName)
    }
}