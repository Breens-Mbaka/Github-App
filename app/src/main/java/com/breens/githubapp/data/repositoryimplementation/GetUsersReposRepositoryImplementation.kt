package com.breens.githubapp.data.repositoryimplementation

import com.breens.githubapp.data.local.dao.ReposDao
import com.breens.githubapp.data.local.mappers.toDomain
import com.breens.githubapp.data.local.mappers.toEntity
import com.breens.githubapp.data.network.GithubApi
import com.breens.githubapp.domain.models.Repository
import com.breens.githubapp.domain.repository.GetUsersReposRepository
import com.breens.githubapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class GetUsersReposRepositoryImplementation(
    private val githubApi: GithubApi,
    private val reposDao: ReposDao
) : GetUsersReposRepository {

    override fun getUsersRepos(name: String?): Flow<Resource<List<Repository>>> = flow {
        emit(Resource.Loading())

        val getReposeFromCache = reposDao.getRepos().map { it.toDomain() }
        Resource.Loading(data = getReposeFromCache)

        try {
            val networkResponse = githubApi.getUsersRepos(name ?: "")
            reposDao.deleteRepos()
            reposDao.storeRepos(networkResponse.map { it.toEntity() })
        } catch (exception: HttpException) {
            emit(
                Resource.Error(
                    message = exception.message(),
                    data = getReposeFromCache,
                    code = exception.code().toString()
                )
            )
        }

        val repos = reposDao.getRepos().map { it.toDomain() }
        emit(Resource.Success(repos))
    }
}