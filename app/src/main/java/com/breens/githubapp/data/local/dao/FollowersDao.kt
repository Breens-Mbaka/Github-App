package com.breens.githubapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.breens.githubapp.data.local.entity.FollowersEntity
import com.breens.githubapp.data.local.entity.FollowingEntity

@Dao
interface FollowersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeUsersFollowing(movies: List<FollowingEntity>)

    @Query("DELETE FROM followingentity")
    suspend fun deleteUsersFollowing()

    @Query("SELECT * FROM followingentity")
    suspend fun getUsersFollowing(): List<FollowingEntity>
}