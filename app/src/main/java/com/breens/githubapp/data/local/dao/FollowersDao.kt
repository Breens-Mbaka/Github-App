package com.breens.githubapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.breens.githubapp.data.local.entity.FollowersEntity

@Dao
interface FollowersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeUsersFollowers(movies: List<FollowersEntity>)

    @Query("DELETE FROM followersentity")
    suspend fun deleteFollowers()

    @Query("SELECT * FROM followersentity")
    suspend fun getUsersFollowers(): List<FollowersEntity>
}