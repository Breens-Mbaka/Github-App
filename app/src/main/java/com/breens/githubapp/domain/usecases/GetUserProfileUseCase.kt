package com.breens.githubapp.domain.usecases

import com.breens.githubapp.domain.models.User
import com.breens.githubapp.domain.repository.GetUserProfileRepository
import com.breens.githubapp.util.Resource
import kotlinx.coroutines.flow.Flow

class GetUserProfileUseCase(private val getUserProfileRepository: GetUserProfileRepository) {

    operator fun invoke(name: String): Flow<Resource<User>> {
        return getUserProfileRepository.getUserProfile(name)
    }
}