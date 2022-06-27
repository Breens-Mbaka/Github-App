package com.breens.githubapp.presentation.viewmodels

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

    private val _searchResult = MutableStateFlow("Breens")
    val searchResult: StateFlow<String?> = _searchResult

    fun searchForGithubUsersFollowing(userName: String): Flow<Resource<List<Following>>> {
        _searchResult.value = userName
        return getUsersFollowingUseCase.invoke(userName)
    }
}