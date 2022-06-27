package com.breens.githubapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.breens.githubapp.domain.usecases.GetUsersFollowingUseCase
import com.breens.githubapp.presentation.viewmodels.GetUsersFollowingViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class GetUsersFollowingViewModelTest {

    private lateinit var getUsersFollowingViewModel: GetUsersFollowingViewModel
    private val getUsersFollowingUseCase: GetUsersFollowingUseCase = mock()

    @get: Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        getUsersFollowingViewModel = GetUsersFollowingViewModel(getUsersFollowingUseCase)
    }

    @Test
    fun `call to fetch users following`() = runTest {
        val userName = "Breens-Mbaka"
        getUsersFollowingViewModel.searchForGithubUsersFollowing(userName)
        verify(getUsersFollowingUseCase).invoke(userName)
    }
}