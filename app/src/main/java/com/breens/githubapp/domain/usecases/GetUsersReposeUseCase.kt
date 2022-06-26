package com.breens.githubapp.domain.usecases

import com.breens.githubapp.domain.models.Repository
import com.breens.githubapp.domain.repository.GetUsersReposRepository
import com.breens.githubapp.util.Resource
import kotlinx.coroutines.flow.Flow

class GetUsersReposeUseCase(private val getUsersReposRepository: GetUsersReposRepository) {

    operator fun invoke(name: String): Flow<Resource<List<Repository>>> {
        return getUsersReposRepository.getUsersRepos(name)
    }
}