package com.breens.githubapp.domain.usecases

import com.breens.githubapp.domain.models.Following
import com.breens.githubapp.domain.repository.GetUsersFollowingRepository
import com.breens.githubapp.util.Resource
import kotlinx.coroutines.flow.Flow

class GetUsersFollowingUseCase(private val getUsersFollowingRepository: GetUsersFollowingRepository) {

    operator fun invoke(name: String): Flow<Resource<List<Following>>> {
        return getUsersFollowingRepository.getUsersFollowing(name)
    }
}