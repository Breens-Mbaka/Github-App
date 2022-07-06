package com.breens.githubapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.breens.githubapp.domain.models.Following
import com.breens.githubapp.domain.usecases.GetUsersFollowingUseCase
import com.breens.githubapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class GetUsersFollowingViewModel @Inject constructor(
    private val getUsersFollowingUseCase: GetUsersFollowingUseCase
) : ViewModel() {

    private val _searchQuery = MutableLiveData("John")
    val searchQuery: LiveData<String?> = _searchQuery

    fun searchForGithubUsersFollowing(userName: String): Flow<Resource<List<Following>>> {
        _searchQuery.value = userName
        return getUsersFollowingUseCase.invoke(userName)
    }
}