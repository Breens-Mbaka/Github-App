package com.breens.githubapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.breens.githubapp.domain.usecases.GetUsersFollowersUseCase
import com.breens.githubapp.presentation.viewmodels.GetUsersFollowersViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class GetUsersFollowersViewModelTest {

    private lateinit var getUsersFollowersViewModel: GetUsersFollowersViewModel
    private val getUsersFollowersUseCase: GetUsersFollowersUseCase = mock()

    @get: Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        getUsersFollowersViewModel = GetUsersFollowersViewModel(getUsersFollowersUseCase)
    }

    @Test
    fun `call to fetch user's followers`() = runTest {
        val userName = "Breens-Mbaka"
        getUsersFollowersViewModel.searchForGithubUserFollowers(userName)
        verify(getUsersFollowersUseCase).invoke(userName)
    }
}