package com.breens.githubapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.breens.githubapp.data.local.dao.UserDao
import com.breens.githubapp.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GithubAppDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}