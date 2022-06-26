package com.breens.githubapp.domain.usecases

import com.breens.githubapp.domain.models.User
import com.breens.githubapp.domain.repository.GetUsersFollowersRepository
import com.breens.githubapp.util.Resource
import kotlinx.coroutines.flow.Flow

class GetUsersFollowersUseCase(private val getUsersFollowersRepository: GetUsersFollowersRepository) {

    operator fun invoke(name: String): Flow<Resource<List<User>>> {
        return getUsersFollowersRepository.getUsersFollowers(name)
    }
}