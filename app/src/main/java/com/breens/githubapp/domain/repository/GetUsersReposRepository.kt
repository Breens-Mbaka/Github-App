package com.breens.githubapp.domain.repository

import com.breens.githubapp.domain.models.Repository
import com.breens.githubapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetUsersReposRepository {

    fun getUsersRepos(name: String?): Flow<Resource<List<Repository>>>
}