package com.breens.githubapp.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.breens.githubapp.data.local.GithubAppDatabase
import com.breens.githubapp.data.local.dao.ReposDao
import com.breens.resources.fakeRepos
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
class ReposDaoTest {

    private lateinit var database: GithubAppDatabase
    private lateinit var reposDao: ReposDao

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        database =
            Room.inMemoryDatabaseBuilder(context, GithubAppDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        reposDao = database.reposDao
    }

    @Test
    fun `store user's repos in GithubAppDatabase`() = runTest {
        reposDao.storeRepos(fakeRepos)

        val usersRepos = reposDao.getRepos()

        Truth.assertThat(usersRepos).isEqualTo(fakeRepos)
    }

    @Test
    fun `delete user's repos in GithubAppDatabase`() = runTest {
        reposDao.storeRepos(fakeRepos)

        reposDao.deleteRepos()

        val usersRepos = reposDao.getRepos()

        Truth.assertThat(usersRepos).isEmpty()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        database.clearAllTables()
        database.close()
    }

}