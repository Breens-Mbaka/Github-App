package com.breens.githubapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.breens.githubapp.domain.models.Followers
import com.breens.githubapp.domain.models.User
import com.breens.githubapp.domain.usecases.GetUserProfileUseCase
import com.breens.githubapp.domain.usecases.GetUsersFollowersUseCase
import com.breens.githubapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class GetUsersFollowersViewModel @Inject constructor(
    private val getUsersFollowersUseCase: GetUsersFollowersUseCase
): ViewModel() {

    private val _searchResult = MutableStateFlow("Breens")
    val searchResult: StateFlow<String?> = _searchResult

    fun searchForGithubUserFollowers(userName: String): Flow<Resource<List<Followers>>> {
        _searchResult.value = userName
        return getUsersFollowersUseCase.invoke(userName)
    }
}