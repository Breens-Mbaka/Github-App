package com.breens.githubapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.breens.githubapp.domain.usecases.GetUserProfileUseCase
import com.breens.githubapp.presentation.viewmodels.GetUserProfileViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class GetUserProfileViewModelTest {

    private lateinit var  getUserProfileViewModel: GetUserProfileViewModel
    private val getUserProfileUseCase: GetUserProfileUseCase = mock()

    @get: Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        getUserProfileViewModel = GetUserProfileViewModel(getUserProfileUseCase)
    }

    @Test
    fun `call to fetch user profile`() = runTest {
        val userName = "Breens-Mbaka"
        getUserProfileViewModel.searchForGithubProfile(userName)
        verify(getUserProfileUseCase).invoke(userName)
    }
}