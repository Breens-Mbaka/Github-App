package com.breens.githubapp.data.local.mappers

import com.breens.githubapp.data.local.entity.*
import com.breens.githubapp.domain.models.*

internal fun UserEntity.toDomain(): User {
    return User(
        this.avatar_url,
        this.bio,
        this.followers,
        this.following,
        this.id,
        this.location,
        this.login,
        this.name,
        this.public_repos
    )
}

internal fun FollowersEntity.toDomain(): Followers {
    return Followers(
        this.id,
        this.avatarUrl,
        this.login
    )
}

internal fun FollowingEntity.toDomain(): Following {
    return Following(
        this.id,
        this.avatarUrl,
        this.login
    )
}

internal fun RepositoryEntity.toDomain(): Repository {
    return Repository(
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

internal fun OwnerEntity.toDomain(): Owner {
    return Owner(
        this.avatarUrl
    )
}