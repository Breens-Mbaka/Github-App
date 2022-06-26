package com.breens.githubapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.breens.githubapp.domain.usecases.GetUsersReposeUseCase
import com.breens.githubapp.presentation.GetUsersReposViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class GetUsersReposViewModelTest {

    private lateinit var getUsersReposViewModel: GetUsersReposViewModel
    private val getUsersReposeUseCase: GetUsersReposeUseCase = mock()

    @get: Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        getUsersReposViewModel = GetUsersReposViewModel(getUsersReposeUseCase)
    }

    @Test
    fun `call to fetch users repos`() = runTest {
        val userName = "Breens-Mbaka"
        getUsersReposViewModel.searchForGithubUsersRepos(userName)
        verify(getUsersReposeUseCase).invoke(userName)
    }
}