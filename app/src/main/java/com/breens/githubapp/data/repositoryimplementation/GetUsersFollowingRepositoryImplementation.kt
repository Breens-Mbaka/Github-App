package com.breens.githubapp.data.repositoryimplementation

import com.breens.githubapp.data.local.dao.FollowingDao
import com.breens.githubapp.data.local.mappers.toDomain
import com.breens.githubapp.data.local.mappers.toEntity
import com.breens.githubapp.data.network.GithubApi
import com.breens.githubapp.domain.models.Following
import com.breens.githubapp.domain.repository.GetUsersFollowingRepository
import com.breens.githubapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class GetUsersFollowingRepositoryImplementation(
    private val githubApi: GithubApi,
    private val followingDao: FollowingDao
) : GetUsersFollowingRepository {

    override fun getUsersFollowing(name: String?): Flow<Resource<List<Following>>> = flow {
        emit(Resource.Loading())

        val getUsersFollowingFromCache = followingDao.getUserFollowing().map { it.toDomain() }
        Resource.Loading(data = getUsersFollowingFromCache)

        try {
            val networkResponse = githubApi.getUsersFollowing(name ?: "")
            followingDao.deleteUserFollowing()
            followingDao.storeUserFollowing(networkResponse.map { it.toEntity() })
        } catch (exception: HttpException) {
            emit(
                Resource.Error(
                    message = exception.message(),
                    data = getUsersFollowingFromCache,
                    code = exception.code().toString()
                )
            )
        }

        val following = followingDao.getUserFollowing().map { it.toDomain() }
        emit(Resource.Success(following))
    }
}