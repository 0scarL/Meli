package com.oscar.meli.di

import android.content.Context
import androidx.room.Room
import com.oscar.meli.data.db.MeliDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleRoom {

    private const val MELI_DATABASE = "meli_database"

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context): MeliDatabase
         = Room.databaseBuilder(context, MeliDatabase::class.java, MELI_DATABASE).build()

    @Provides
    @Singleton
    fun provideProductDao(db : MeliDatabase) = db.getProductDao()

}