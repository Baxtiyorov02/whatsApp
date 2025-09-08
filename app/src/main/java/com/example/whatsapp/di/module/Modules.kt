package com.example.whatsapp.di.module

import android.content.Context
import androidx.room.Room
import com.example.whatsapp.data.lokal.db.AppDatabase
import com.example.whatsapp.data.lokal.db.UserProfileDao
import com.example.whatsapp.data.repository.AuthRepository
import com.example.whatsapp.data.repository.AuthRepositoryImpl
import com.example.whatsapp.data.repository.chat.MessageRepository
import com.example.whatsapp.data.repository.chat.MessageRepositoryImpl
import com.example.whatsapp.data.repository.user_profile.UserProfileRepository
import com.example.whatsapp.data.repository.user_profile.UserProfileRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase:: class.java,
            "app_data"
        ).build()
    }

    @Provides
    fun provideUserProfileDao(db: AppDatabase): UserProfileDao = db.userProfileDao()

    @Provides
    @Singleton
    fun provideUserProfileRepository(dao: UserProfileDao): UserProfileRepository =
        UserProfileRepositoryImpl(dao)


    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl()

    @Provides
    @Singleton
    fun provideMessageRepository(): MessageRepository = MessageRepositoryImpl()
}