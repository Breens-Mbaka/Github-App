package com.breens.resources

import com.breens.githubapp.data.local.entity.FollowersEntity
import com.breens.githubapp.data.local.entity.FollowingEntity
import com.breens.githubapp.data.local.entity.UserEntity

val fakeUserProfile = UserEntity(
    id = 72180010,
    avatarUrl = "https://avatars.githubusercontent.com/u/72180010?v=4",
    login = "Breens-Mbaka",
    name = "Breens Robert",
    location = "Kenya",
    bio = "Android Developer",
    publicRepos = 125
)

val fakeUsersFollowers = listOf(
    FollowersEntity(
        id = 72180010,
        avatarUrl = "https://avatars.githubusercontent.com/u/72180010?v=4",
        login = "Breens-Mbaka",
        name = "Breens Robert",
        location = "Kenya",
        bio = "Android Developer",
        publicRepos = 125
    )
)

val fakeUsersFollowing = listOf(
    FollowingEntity(
        id = 72180010,
        avatarUrl = "https://avatars.githubusercontent.com/u/72180010?v=4",
        login = "Breens-Mbaka",
        name = "Breens Robert",
        location = "Kenya",
        bio = "Android Developer",
        publicRepos = 125
    )
)