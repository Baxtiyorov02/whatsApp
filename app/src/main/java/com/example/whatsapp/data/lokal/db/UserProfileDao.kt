package com.example.whatsapp.data.lokal.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: UserProfile)

    @Query("SELECT * FROM user_profile LIMIT 1")
     fun getProfile(): Flow<UserProfile?>

    @Query("DELETE FROM user_profile")
    suspend fun clearProfile()
}