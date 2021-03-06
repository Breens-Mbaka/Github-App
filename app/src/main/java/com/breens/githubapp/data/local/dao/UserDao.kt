package com.breens.githubapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.breens.githubapp.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("DELETE FROM userentity")
    suspend fun deleteUser()

    @Query("SELECT * FROM userentity where login =:query")
    suspend fun getUser(query: String?): UserEntity?

}