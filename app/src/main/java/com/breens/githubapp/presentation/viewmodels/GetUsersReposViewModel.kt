package com.breens.githubapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.breens.githubapp.domain.models.Repository
import com.breens.githubapp.domain.usecases.GetUsersReposeUseCase
import com.breens.githubapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GetUsersReposViewModel @Inject constructor(
    private val getUsersReposUseCase: GetUsersReposeUseCase
) : ViewModel() {

    fun searchForGithubUsersRepos(userName: String): Flow<Resource<List<Repository>>> {
        return getUsersReposUseCase.invoke(userName)
    }
}