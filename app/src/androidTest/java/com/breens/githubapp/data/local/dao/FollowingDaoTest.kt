package com.breens.githubapp.data.local.dao

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.breens.githubapp.data.local.GithubAppDatabase
import com.breens.githubapp.resources.fakeUsersFollowing
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FollowingDaoTest {

    private lateinit var database: GithubAppDatabase
    private lateinit var followingDao: FollowingDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder(context, GithubAppDatabase::class.java)
            .allowMainThreadQueries().build()
        followingDao = database.followingDao
    }

    @After
    fun tearDown() {
        runBlocking {
            followingDao.deleteUserFollowing()
            database.close()
        }
    }

    @Test
    fun storeUsersFollowers() {
        runBlocking {
            val followersTestFake = fakeUsersFollowing

            val followingTestListUtil = listOf(followersTestFake)
            followingDao.storeUserFollowing(followingTestListUtil)

            val result = followingDao.getUserFollowing()
            Assert.assertEquals(followingTestListUtil, result)
        }
    }

    @Test
    fun deleteUserFollowing() {
        runBlocking {
            followingDao.storeUserFollowing(listOf(fakeUsersFollowing))
            followingDao.deleteUserFollowing()
            val usersFollowing = followingDao.getUserFollowing()
            assertThat(usersFollowing).isEmpty()
        }
    }

}