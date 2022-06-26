package com.breens.githubapp.domain.repository

import com.breens.githubapp.domain.models.Following
import com.breens.githubapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetUsersFollowingRepository {

    fun getUsersFollowing(name: String?): Flow<Resource<List<Following>>>
}