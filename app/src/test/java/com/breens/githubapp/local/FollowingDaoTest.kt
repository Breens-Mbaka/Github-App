package com.breens.githubapp.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.breens.githubapp.data.local.GithubAppDatabase
import com.breens.githubapp.data.local.dao.FollowingDao
import com.breens.githubapp.resources.fakeUsersFollowing
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class FollowingDaoTest {

    private lateinit var database: GithubAppDatabase
    private lateinit var followingDao: FollowingDao

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        database =
            Room.inMemoryDatabaseBuilder(context, GithubAppDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        followingDao = database.followingDao
    }

    @Test
    fun `store user's following in GithubAppDatabase`() = runTest {
        followingDao.storeUserFollowing(com.breens.githubapp.resources.fakeUsersFollowing)

        val usersFollowing = followingDao.getUserFollowing()

        Truth.assertThat(usersFollowing).isEqualTo(com.breens.githubapp.resources.fakeUsersFollowing)
    }

    @Test
    fun `delete user's following in GithubAppDatabase`() = runTest {
        followingDao.storeUserFollowing(com.breens.githubapp.resources.fakeUsersFollowing)

        followingDao.deleteUserFollowing()

        val usersFollowing = followingDao.getUserFollowing()

        Truth.assertThat(usersFollowing).isEmpty()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        database.clearAllTables()
        database.close()
    }

}