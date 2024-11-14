package com.sumit.daggerHiltStructure.modules

import android.content.Context
import androidx.room.Room
import com.sumit.daggerHiltStructure.network.ApiService
import com.sumit.daggerHiltStructure.ui.model.AppDatabase
import com.sumit.daggerHiltStructure.ui.model.UserDao
import com.sumit.daggerHiltStructure.ui.repo.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }


    @Provides
    @Singleton
    fun provideUserRepository(
        @ApplicationContext context: Context,
        userDao: UserDao,
        apiService: ApiService
    ): UserRepository {
        return UserRepository(context, userDao, apiService)
    }
}