package com.breens.githubapp.domain.usecases

import com.breens.githubapp.domain.models.User
import com.breens.githubapp.domain.repository.GetUserProfileRepository
import kotlinx.coroutines.flow.Flow

class GetUserProfileUseCase(private val getUserProfileRepository: GetUserProfileRepository) {

    operator fun invoke(name: String): Flow<User> {
        return getUserProfileRepository.getUserProfile(name)
    }
}