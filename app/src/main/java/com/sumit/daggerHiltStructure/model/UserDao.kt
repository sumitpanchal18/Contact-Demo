package com.sumit.daggerHiltStructure.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User): Long  // Returns the row ID of the inserted user

    @Update
    suspend fun update(user: User): Int  // Returns the number of rows affected

    @Delete
    suspend fun delete(user: User): Int  // Returns the number of rows affected

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>  // Returns a Flow of List<User>
}
