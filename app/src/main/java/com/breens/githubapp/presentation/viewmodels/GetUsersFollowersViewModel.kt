package com.breens.githubapp.presentation.viewmodels

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

    fun searchForGithubUserFollowers(userName: String): Flow<Resource<List<Followers>>> {
        return getUsersFollowersUseCase.invoke(userName)
    }
}