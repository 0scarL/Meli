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

/**
 * Módulo de Dagger Hilt que proporciona las dependencias necesarias para configurar y acceder a la
 * base de datos local utilizando Room, junto con los DAO (Data Access Objects) necesarios para
 * interactuar con las tablas de productos y detalles.
 *
 * Este módulo se instala en el componente `SingletonComponent`, lo que significa que las dependencias
 * proporcionadas son de alcance global para toda la aplicación.
 */
@Module
@InstallIn(SingletonComponent::class)
object ModuleRoom {

    private const val MELI_DATABASE = "meli_database"


    /**
     * Proporciona una instancia de la base de datos MeliDatabase utilizando Room.
     *
     * @param context Contexto de la aplicación utilizado para crear la base de datos.
     * @return Instancia de la base de datos MeliDatabase que contiene las tablas y DAO necesarios.
     */
    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context): MeliDatabase
         = Room.databaseBuilder(context, MeliDatabase::class.java, MELI_DATABASE).build()

    @Provides
    @Singleton
    fun provideProductDao(db : MeliDatabase) = db.getProductDao()

    @Provides
    @Singleton
    fun provideDetailDao(db : MeliDatabase) = db.getDetailDao()

}