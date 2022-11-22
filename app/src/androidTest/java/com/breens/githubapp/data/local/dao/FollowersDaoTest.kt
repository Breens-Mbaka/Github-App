package com.breens.githubapp.data.local.dao

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.breens.githubapp.data.local.GithubAppDatabase
import com.breens.githubapp.resources.fakeUsersFollowers
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FollowersDaoTest {

    private lateinit var database: GithubAppDatabase
    private lateinit var followersDao: FollowersDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder(context, GithubAppDatabase::class.java)
            .allowMainThreadQueries().build()
        followersDao = database.followersDao
    }

    @After
    fun tearDown() {
        runBlocking {
            followersDao.deleteFollowers()
            database.close()
        }
    }

    @Test
    fun storeUsersFollowers() {
        runBlocking {
            val followersTestFake = fakeUsersFollowers

            val reposTestListUtil = listOf(followersTestFake)
            followersDao.storeUsersFollowers(reposTestListUtil)

            val result = followersDao.getUsersFollowers()
            Assert.assertEquals(reposTestListUtil, result)
        }
    }


    @Test
    fun deleteFollowers() {
        runBlocking {
            followersDao.storeUsersFollowers(listOf(fakeUsersFollowers))
            followersDao.deleteFollowers()
            val usersFollowers = followersDao.getUsersFollowers()
            assertThat(usersFollowers.isEmpty())
        }
    }

}