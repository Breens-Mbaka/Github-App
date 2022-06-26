package com.breens.resources

import com.breens.githubapp.data.local.entity.*

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


val fakeRepoOwner = OwnerEntity(
    avatarUrl = "https://avatars.githubusercontent.com/u/72180010?v=4"
)

val fakeRepos = listOf(
    RepositoryEntity(
        id = 72180010,
        name = "30-Days-Of-JavaScript",
        fullName = "Breens-Mbaka/30-Days-Of-JavaScript",
        owner = fakeRepoOwner,
        stargazersCount = 0,
        watchersCount = 0,
        forksCount = 0,
        language = "Javascript",
        openIssues = 1,
        description = "something",
        updatedAt = "2021-01-13T14:11:02Z"
    )
)