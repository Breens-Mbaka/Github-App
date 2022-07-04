package com.breens.githubapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.breens.githubapp.domain.models.User
import com.breens.githubapp.domain.usecases.GetUserProfileUseCase
import com.breens.githubapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GetUserProfileViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    fun searchForGithubProfile(userName: String): Flow<Resource<User>> {
        return getUserProfileUseCase.invoke(userName)
    }
}