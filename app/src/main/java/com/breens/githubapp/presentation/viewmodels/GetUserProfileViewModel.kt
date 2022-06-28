package com.breens.githubapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.breens.githubapp.domain.models.User
import com.breens.githubapp.domain.usecases.GetUserProfileUseCase
import com.breens.githubapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class GetUserProfileViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    private val _user = MutableStateFlow("Breens-Mbaka")
    val user: StateFlow<String?> = _user

    fun searchForGithubProfile(userName: String): Flow<Resource<User>> {
        _user.value = userName
        return getUserProfileUseCase.invoke(userName)
    }
}