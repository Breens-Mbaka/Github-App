package com.breens.githubapp.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.breens.githubapp.data.local.GithubAppDatabase
import com.breens.githubapp.data.local.dao.UserDao
import com.breens.resources.fakeUserProfile
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
class UserDaoTest {

    private lateinit var database: GithubAppDatabase
    private lateinit var userDao: UserDao

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        database =
            Room.inMemoryDatabaseBuilder(context, GithubAppDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        userDao = database.userDao
    }

    @Test
    fun `store user profile in GithubAppDatabase`() = runTest {
        userDao.insertUser(fakeUserProfile)

        val userProfile = userDao.getUser("Breens-Mbaka")

        Truth.assertThat(userProfile).isEqualTo(fakeUserProfile)
    }

    @Test
    fun `delete user profile in GithubAppDatabase`() = runTest {
        userDao.insertUser(fakeUserProfile)

        userDao.deleteUser()

        val userProfile = userDao.getUser("Breens-Mbaka")

        Truth.assertThat(userProfile).isNull()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        database.clearAllTables()
        database.close()
    }

}