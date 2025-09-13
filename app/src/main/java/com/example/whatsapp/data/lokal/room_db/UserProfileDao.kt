package com.example.whatsapp.data.lokal.room_db

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

    @Query("UPDATE user_profile SET nickName = :nickName WHERE id = 1")
    suspend fun updateNickName(nickName: String)

    @Query("SELECT   avatarUrl FROM user_profile WHERE id = 1")
      fun getAvatarUrl(): Flow<String?>

    @Query("UPDATE user_profile SET avatarUrl = :avatarUrl WHERE id = 1")
    suspend fun updateAvatarUrl(avatarUrl: String?)




}