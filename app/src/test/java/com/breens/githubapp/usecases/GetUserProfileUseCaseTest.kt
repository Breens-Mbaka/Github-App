package com.breens.githubapp.usecases

import com.breens.githubapp.domain.repository.GetUserProfileRepository
import com.breens.githubapp.domain.usecases.GetUserProfileUseCase
import com.breens.githubapp.util.Resource
import io.mockk.mockk
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetUserProfileUseCaseTest {

    companion object {
        fun mockGetUserProfileRepository(
            flowReturn: Flow<Resource<User>>
        ) = object : GetUserProfileRepository {

            override fun getUserProfile(name: String?): Flow<Resource<User>> = flowReturn
        }
    }

    @Test
    fun  `Get user profile starts with loading RETURNS Resource Loading`() = runBlocking {
        val user = mockk<User>()
        val name = "Breens-Mbaka"

        val userProfileRepository = mockGetUserProfileRepository(flow {
            emit(Resource.Loading())
            emit(Resource.Success(user))
        })

        val result = GetUserProfileUseCase(userProfileRepository).invoke(name).first()

        assert((result is Resource.Loading))
    }

    @Test
    fun `get user profile success result RETURNS Resource + Data`() = runBlocking {
        val user = mockk<User>()
        val name = "Breens-Mbaka"
        val userProfileRepository = mockGetUserProfileRepository(flow {
            emit(Resource.Loading())
            emit(Resource.Success(user))
        })

        val result =  GetUserProfileUseCase(userProfileRepository).invoke(name).last()

        assert(result is Resource.Success && (result.data ?: false) != emptyFlow<User>())
    }

    @Test
    fun `get user profile error RETURNS Resource Error`() = runBlocking {
        val name = "Breens-Mbaka"

        val userProfileRepository = mockGetUserProfileRepository(flow {
            emit(Resource.Error("Error getting user profile"))
        })

        val result =  GetUserProfileUseCase(userProfileRepository).invoke(name).last()

        assert(result is Resource.Error && result.message == "Error getting user profile")
    }
}