package com.breens.githubapp.usecases

import com.breens.githubapp.domain.models.Repository
import com.breens.githubapp.domain.repository.GetUsersReposRepository
import com.breens.githubapp.domain.usecases.GetUsersReposeUseCase
import com.breens.githubapp.util.Resource
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetUsersReposUseCaseTest {

    companion object {
        fun mockGetUsersReposRepository(
            flowReturn: Flow<Resource<List<Repository>>>
        ) = object : GetUsersReposRepository {

            override fun getUsersRepos(name: String?): Flow<Resource<List<Repository>>> = flowReturn
        }
    }

    @Test
    fun `Get user's repos following starts with loading RETURNS Resource Loading`() = runBlocking {
        val usersRepos = mockk<List<Repository>>()
        val name = "Breens-Mbaka"

        val getUsersReposRepository = mockGetUsersReposRepository(flow {
            emit(Resource.Loading())
            emit(Resource.Success(usersRepos))
        })

        val result = GetUsersReposeUseCase(getUsersReposRepository).invoke(name).first()

        assert((result is Resource.Loading))
    }

    @Test
    fun `get user's repos success result RETURNS Resource + Data`() = runBlocking {
        val usersRepos = mockk<List<Repository>>()
        val name = "Breens-Mbaka"

        val getUsersReposRepository = mockGetUsersReposRepository(flow {
            emit(Resource.Loading())
            emit(Resource.Success(usersRepos))
        })

        val result = GetUsersReposeUseCase(getUsersReposRepository).invoke(name).last()

        assert(result is Resource.Success && (result.data ?: false) != emptyList<Repository>())
    }

    @Test
    fun `get user's repos error RETURNS Resource Error`() = runBlocking {
        val name = "Breens-Mbaka"

        val getUsersReposRepository = mockGetUsersReposRepository(flow {
            emit(Resource.Error("Error getting users repos"))
        })

        val result = GetUsersReposeUseCase(getUsersReposRepository).invoke(name).last()

        assert(result is Resource.Error && result.message == "Error getting users repos")
    }
}