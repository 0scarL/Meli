package com.oscar.meli.di

import com.oscar.meli.data.client.retrofit.RetrofitConstant.URL_BASE
import com.oscar.meli.data.datasource.products.ProductDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleRetrofit {

    @Provides
    @Singleton
    fun providerRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providerProductDataSource(retrofit: Retrofit): ProductDataSource {
        return retrofit.create(ProductDataSource::class.java)

    }

}