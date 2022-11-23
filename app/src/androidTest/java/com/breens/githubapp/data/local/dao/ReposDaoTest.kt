package com.breens.githubapp.data.local.dao

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.breens.githubapp.data.local.GithubAppDatabase
import com.breens.githubapp.resources.fakeRepos
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ReposDaoTest {

    private lateinit var database: GithubAppDatabase
    private lateinit var reposDao: ReposDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder(context, GithubAppDatabase::class.java)
            .allowMainThreadQueries().build()
        reposDao = database.reposDao
    }

    @After
    fun tearDown() {
        runBlocking {
            reposDao.deleteRepos()
            database.close()
        }
    }

    @Test
    fun `storeRepos`() {
        runBlocking {
            val reposTestFake = fakeRepos

            val reposTestListUtil = listOf(reposTestFake)
            reposDao.storeRepos(reposTestListUtil)

            val result = reposDao.getRepos()
            Assert.assertEquals(reposTestListUtil, result)
        }
    }

    @Test
    fun `deleteRepos`() {
        runBlocking {
            reposDao.storeRepos(listOf(fakeRepos))
            reposDao.deleteRepos()
            val usersRepos = reposDao.getRepos()

            Truth.assertThat(usersRepos).isEmpty()
        }
    }


}