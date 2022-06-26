package com.breens.githubapp.domain.repository

import com.breens.githubapp.domain.models.Followers
import com.breens.githubapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetUsersFollowersRepository {

    fun getUsersFollowers(name: String?): Flow<Resource<List<Followers>>>
}