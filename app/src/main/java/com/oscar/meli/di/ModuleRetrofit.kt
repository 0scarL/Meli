package com.oscar.meli.di

import com.oscar.meli.data.client.retrofit.RetrofitConstant.URL_BASE
import com.oscar.meli.data.datasource.api.detail.DetailApiDataSource
import com.oscar.meli.data.datasource.api.product.ProductApiDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Módulo de Dagger Hilt que proporciona las dependencias necesarias para la configuración de Retrofit
 * y las fuentes de datos de la API (ProductApiDataSource y DetailApiDataSource).
 *
 * Este módulo se instala en el componente `SingletonComponent`, lo que significa que las dependencias
 * proporcionadas son de alcance global para toda la aplicación.
 */
@Module
@InstallIn(SingletonComponent::class)
object ModuleRetrofit {


    /**
     * Proporciona una instancia de Retrofit configurada para interactuar con las APIs.
     *
     * @return Instancia de Retrofit configurada con la URL base y el conversor Gson.
     */
    @Provides
    @Singleton
    fun providerRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Proporciona una instancia de ProductApiDataSource utilizando Retrofit.
     *
     * @param retrofit Instancia de Retrofit utilizada para crear el servicio.
     * @return Instancia de ProductApiDataSource que permite la comunicación con la API de productos.
     */
    @Provides
    @Singleton
    fun providerProductDataSource(retrofit: Retrofit): ProductApiDataSource {
        return retrofit.create(ProductApiDataSource::class.java)

    }

    /**
     * Proporciona una instancia de DetailApiDataSource utilizando Retrofit.
     *
     * @param retrofit Instancia de Retrofit utilizada para crear el servicio.
     * @return Instancia de DetailApiDataSource que permite la comunicación con la API de detalles del producto.
     */

    @Provides
    @Singleton
    fun provideDetailDataSource(retrofit: Retrofit): DetailApiDataSource {
        return retrofit.create(DetailApiDataSource::class.java)
    }

}