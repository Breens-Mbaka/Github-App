package com.breens.githubapp.data.local.mappers

import com.breens.githubapp.data.local.entity.*
import com.breens.githubapp.data.network.response.OwnerDto
import com.breens.githubapp.data.network.response.RepositoryDto
import com.breens.githubapp.domain.models.Followers
import com.breens.githubapp.domain.models.Following
import com.breens.githubapp.domain.models.User

internal fun UserEntity.toDomain(): User {
    return User(
        this.id,
        this.avatarUrl,
        this.login,
        this.name,
        this.location,
        this.bio,
        this.publicRepos
    )
}

internal fun FollowersEntity.toDomain(): Followers {
    return Followers(
        this.id,
        this.avatarUrl,
        this.login,
        this.name,
        this.location,
        this.bio,
        this.publicRepos
    )
}

internal fun FollowingEntity.toDomain(): Following {
    return Following(
        this.id,
        this.avatarUrl,
        this.login,
        this.name,
        this.location,
        this.bio,
        this.publicRepos
    )
}

internal fun RepositoryEntity.toDomain(): RepositoryDto {
    return RepositoryDto(
        this.id,
        this.name,
        this.fullName,
        this.owner.toDomain(),
        this.stargazersCount,
        this.watchersCount,
        this.forksCount,
        this.language,
        this.openIssues,
        this.description,
        this.updatedAt,
    )
}

internal fun OwnerEntity.toDomain(): OwnerDto {
    return OwnerDto(
        this.avatarUrl
    )
}