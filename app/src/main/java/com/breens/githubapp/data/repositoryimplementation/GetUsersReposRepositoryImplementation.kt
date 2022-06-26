package com.breens.githubapp.data.repositoryimplementation

import com.breens.githubapp.data.local.dao.FollowersDao
import com.breens.githubapp.data.local.mappers.toDomain
import com.breens.githubapp.data.local.mappers.toEntity
import com.breens.githubapp.data.network.GithubApi
import com.breens.githubapp.domain.models.Followers
import com.breens.githubapp.domain.repository.GetUsersFollowersRepository
import com.breens.githubapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetUsersReposRepositoryImplementation(
    private val githubApi: GithubApi,
    private val followersDao: FollowersDao
) : GetUsersFollowersRepository {

    override fun getUsersFollowers(name: String?): Flow<Resource<List<Followers>>> = flow {
        emit(Resource.Loading())

        val getFollowersFromCache = followersDao.getUsersFollowers().map { it.toDomain() }
        Resource.Loading(data = getFollowersFromCache)

        try {
            val networkResponse = githubApi.getUsersFollowers(name ?: "")
            followersDao.deleteFollowers()
            followersDao.storeUsersFollowers(networkResponse.map { it.toEntity() })
        } catch (exception: HttpException) {
            emit(
                Resource.Error(
                    message = exception.message(),
                    data = getFollowersFromCache
                )
            )
        } catch (exception: IOException) {
            emit(
                Resource.Error(
                    message = exception.message.toString(),
                    data = getFollowersFromCache
                )
            )
        }

        val followers = followersDao.getUsersFollowers().map { it.toDomain() }
        emit(Resource.Success(followers))
    }
}