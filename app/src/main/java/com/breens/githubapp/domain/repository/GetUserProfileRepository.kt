package com.breens.githubapp.domain.repository

import com.breens.githubapp.domain.models.User
import com.breens.githubapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetUserProfileRepository {

    fun getUserProfile(name: String?): Flow<Resource<User>>
}