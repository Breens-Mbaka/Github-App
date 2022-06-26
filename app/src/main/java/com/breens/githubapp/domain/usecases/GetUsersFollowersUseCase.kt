package com.breens.githubapp.domain.usecases

import com.breens.githubapp.domain.models.Followers
import com.breens.githubapp.domain.repository.GetUsersFollowersRepository
import com.breens.githubapp.util.Resource
import kotlinx.coroutines.flow.Flow

class GetUsersFollowersUseCase(private val getUsersFollowersRepository: GetUsersFollowersRepository) {

    operator fun invoke(name: String): Flow<Resource<List<Followers>>> {
        return getUsersFollowersRepository.getUsersFollowers(name)
    }
}