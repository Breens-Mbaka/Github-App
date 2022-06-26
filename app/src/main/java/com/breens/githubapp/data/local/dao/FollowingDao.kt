package com.breens.githubapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.breens.githubapp.data.local.entity.FollowingEntity

@Dao
interface FollowingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeUserFollowing(following: List<FollowingEntity>)

    @Query("DELETE FROM followingentity")
    suspend fun deleteUserFollowing()

    @Query("SELECT * FROM followingentity")
    suspend fun getUserFollowing(): List<FollowingEntity>
}