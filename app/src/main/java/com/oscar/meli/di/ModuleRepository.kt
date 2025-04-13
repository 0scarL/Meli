package com.oscar.meli.di

import com.oscar.meli.data.repository.detail.DetailRepository
import com.oscar.meli.data.repository.detail.DetailRepositoryImp
import com.oscar.meli.data.repository.product.ProductRepository
import com.oscar.meli.data.repository.product.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleRepository {

    @Provides
    @Singleton
    fun provideProducRepository(impl : ProductRepositoryImpl) : ProductRepository = impl

    @Provides
    @Singleton
    fun provideDetailRepository(impl : DetailRepositoryImp) : DetailRepository = impl


}