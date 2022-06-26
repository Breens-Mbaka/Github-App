package com.breens.githubapp.presentation

import androidx.lifecycle.ViewModel
import com.breens.githubapp.domain.models.Repository
import com.breens.githubapp.domain.usecases.GetUsersReposeUseCase
import com.breens.githubapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class GetUsersReposViewModel @Inject constructor(
    private val getUsersReposUseCase: GetUsersReposeUseCase
) : ViewModel() {

    private val _searchResult = MutableStateFlow("Breens")
    val searchResult: StateFlow<String?> = _searchResult

    fun searchForGithubUsersRepos(userName: String): Flow<Resource<List<Repository>>> {
        _searchResult.value = userName
        return getUsersReposUseCase.invoke(userName)
    }
}