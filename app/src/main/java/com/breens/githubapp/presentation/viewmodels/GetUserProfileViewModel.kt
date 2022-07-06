package com.breens.githubapp.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.breens.githubapp.R
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

    private val _searchQuery = MutableLiveData("")
    val searchQuery: LiveData<String?> = _searchQuery

    fun searchForGithubProfile(userName: String): Flow<Resource<User>> {
        _searchQuery.value = userName
        return getUserProfileUseCase.invoke(userName)
    }
}