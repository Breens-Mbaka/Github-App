package com.breens.githubapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.breens.githubapp.data.local.entity.RepositoryEntity

@Dao
interface ReposDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeRepos(repos: List<RepositoryEntity>)

    @Query("DELETE FROM repositoryentity")
    suspend fun deleteRepos()

    @Query("SELECT * FROM repositoryentity")
    suspend fun getRepos(): List<RepositoryEntity>
}