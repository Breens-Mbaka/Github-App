package com.breens.githubapp.data.local.dao

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.breens.githubapp.data.local.GithubAppDatabase
import com.breens.githubapp.resources.fakeUserProfile
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class UserDaoTest {

    private lateinit var database: GithubAppDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder(context, GithubAppDatabase::class.java)
            .allowMainThreadQueries().build()
        userDao = database.userDao
    }

    @After
    fun tearDown() {
        runBlocking {
            userDao.deleteUser()
            database.close()
        }
    }

    @Test
    fun `insertUser`() {
        runBlocking {
            userDao.insertUser(fakeUserProfile)
            val userProfile = userDao.getUser("Breens-Mbaka")

            Truth.assertThat(userProfile).isEqualTo(fakeUserProfile)

        }
    }

    @Test
    fun `deleteUser`() {
        runBlocking {
            userDao.insertUser(fakeUserProfile)
            userDao.deleteUser()
            val userProfile = userDao.getUser("Breens-Mbaka")

            Truth.assertThat(userProfile).isNull()
        }
    }

}