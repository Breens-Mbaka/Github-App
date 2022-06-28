package com.breens.githubapp.usecases

import com.breens.githubapp.domain.models.Followers
import com.breens.githubapp.domain.repository.GetUsersFollowersRepository
import com.breens.githubapp.domain.usecases.GetUsersFollowersUseCase
import com.breens.githubapp.util.Resource
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetUserFollowersUseCaseTest {

    companion object {
        fun mockGetUsersFollowersRepository(
            flowReturn: Flow<Resource<List<Followers>>>
        ) = object : GetUsersFollowersRepository {

            override fun getUsersFollowers(name: String?): Flow<Resource<List<Followers>>> = flowReturn
        }
    }

    @Test
    fun `Get users followers starts with loading RETURNS Resource Loading`() = runBlocking {
        val followers = mockk<List<Followers>>()
        val name = "Breens-Mbaka"

        val userFollowersRepository = mockGetUsersFollowersRepository(flow {
            emit(Resource.Loading())
            emit(Resource.Success(followers))
        })

        val result = GetUsersFollowersUseCase(userFollowersRepository).invoke(name).first()

        assert((result is Resource.Loading))
    }

    @Test
    fun `get users followers success result RETURNS Resource + Data`() = runBlocking {
        val followers = mockk<List<Followers>>()
        val name = "Breens-Mbaka"
        val userFollowersRepository = mockGetUsersFollowersRepository(flow {
            emit(Resource.Loading())
            emit(Resource.Success(followers))
        })

        val result = GetUsersFollowersUseCase(userFollowersRepository).invoke(name).last()

        assert(result is Resource.Success && (result.data ?: false) != emptyList<User>())
    }

    @Test
    fun `get users profile error RETURNS Resource Error`() = runBlocking {
        val name = "Breens-Mbaka"

        val userFollowersRepository = mockGetUsersFollowersRepository(flow {
            emit(Resource.Error("Error getting users followers"))
        })

        val result = GetUsersFollowersUseCase(userFollowersRepository).invoke(name).last()

        assert(result is Resource.Error && result.message == "Error getting users followers")
    }
}