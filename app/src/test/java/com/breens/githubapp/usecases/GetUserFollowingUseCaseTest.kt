package com.breens.githubapp.usecases

import com.breens.githubapp.domain.models.Following
import com.breens.githubapp.domain.models.User
import com.breens.githubapp.domain.repository.GetUsersFollowingRepository
import com.breens.githubapp.domain.usecases.GetUsersFollowingUseCase
import com.breens.githubapp.util.Resource
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetUserFollowingUseCaseTest {

    companion object {
        fun mockGetUsersFollowingRepository(
            flowReturn: Flow<Resource<List<Following>>>
        ) = object : GetUsersFollowingRepository {

            override fun getUsersFollowing(name: String?): Flow<Resource<List<Following>>> =
                flowReturn
        }
    }

    @Test
    fun `Get users following starts with loading RETURNS Resource Loading`() = runBlocking {
        val usersFollowing = mockk<List<Following>>()
        val name = "Breens-Mbaka"

        val userFollowingRepository = mockGetUsersFollowingRepository(flow {
            emit(Resource.Loading())
            emit(Resource.Success(usersFollowing))
        })

        val result = GetUsersFollowingUseCase(userFollowingRepository).invoke(name).first()

        assert((result is Resource.Loading))
    }

    @Test
    fun `get users following success result RETURNS Resource + Data`() = runBlocking {
        val following = mockk<List<Following>>()
        val name = "Breens-Mbaka"

        val userFollowingRepository = mockGetUsersFollowingRepository(flow {
            emit(Resource.Loading())
            emit(Resource.Success(following))
        })

        val result = GetUsersFollowingUseCase(userFollowingRepository).invoke(name).last()

        assert(result is Resource.Success && (result.data ?: false) != emptyList<User>())
    }

    @Test
    fun `get users following error RETURNS Resource Error`() = runBlocking {
        val following = mockk<List<Following>>()
        val name = "Breens-Mbaka"

        val userFollowingRepository = mockGetUsersFollowingRepository(flow {
            emit(Resource.Error("Error getting users following", following, "200"))
        })

        val result = GetUsersFollowingUseCase(userFollowingRepository).invoke(name).last()

        assert(result is Resource.Error && result.message == "Error getting users following")
    }
}