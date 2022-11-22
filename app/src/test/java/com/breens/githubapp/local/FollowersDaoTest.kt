package com.breens.githubapp.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.breens.githubapp.data.local.GithubAppDatabase
import com.breens.githubapp.data.local.dao.FollowersDao
import com.breens.githubapp.resources.fakeUsersFollowers
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
class FollowersDaoTest {

    private lateinit var database: GithubAppDatabase
    private lateinit var followersDao: FollowersDao

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        database =
            Room.inMemoryDatabaseBuilder(context, GithubAppDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        followersDao = database.followersDao
    }

    @Test
    fun `store user's followers in GithubAppDatabase`() = runTest {
        followersDao.storeUsersFollowers(com.breens.githubapp.resources.fakeUsersFollowers)

        val usersFollowers = followersDao.getUsersFollowers()

        Truth.assertThat(usersFollowers).isEqualTo(com.breens.githubapp.resources.fakeUsersFollowers)
    }

    @Test
    fun `delete user's followers in GithubAppDatabase`() = runTest {
        followersDao.storeUsersFollowers(com.breens.githubapp.resources.fakeUsersFollowers)

        followersDao.deleteFollowers()

        val usersFollowers = followersDao.getUsersFollowers()

        Truth.assertThat(usersFollowers).isEmpty()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        database.clearAllTables()
        database.close()
    }

}