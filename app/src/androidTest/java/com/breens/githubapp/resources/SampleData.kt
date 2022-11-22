package com.breens.githubapp.resources

import com.breens.githubapp.data.local.entity.*

val fakeUserProfile = UserEntity(
    avatar_url = "https://avatars.githubusercontent.com/u/72180010?v=4",
    bio = "Android Developer",
    followers = 23,
    following = 12,
    id = 72180010,
    location = "Kenya",
    login = "Breens-Mbaka",
    name = "Breens Robert",
    public_repos = 125
)

val fakeUsersFollowers =
    FollowersEntity(
        id = 72180010,
        avatarUrl = "https://avatars.githubusercontent.com/u/72180010?v=4",
        login = "Breens-Mbaka",

)

val fakeUsersFollowing =
    FollowingEntity(
        id = 72180010,
        avatarUrl = "https://avatars.githubusercontent.com/u/72180010?v=4",
        login = "Breens-Mbaka",
    )



val fakeRepoOwner = OwnerEntity(
    avatarUrl = "https://avatars.githubusercontent.com/u/72180010?v=4"
)

val fakeRepos =
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
