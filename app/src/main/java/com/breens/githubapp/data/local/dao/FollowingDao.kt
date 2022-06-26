package com.breens.githubapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.breens.githubapp.data.local.entity.FollowingEntity

@Dao
interface FollowingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeUserFollowing(movies: List<FollowingEntity>)

    @Query("DELETE FROM followersentity")
    suspend fun deleteUserFollowing()

    @Query("SELECT * FROM followersentity")
    suspend fun getUserFollowing(): List<FollowingEntity>
}