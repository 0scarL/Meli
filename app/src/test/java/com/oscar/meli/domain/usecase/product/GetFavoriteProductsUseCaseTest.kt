package com.oscar.meli.domain.usecase.product

import com.oscar.meli.data.db.ProductPlainEntity
import com.oscar.meli.data.repository.product.ProductRepository
import com.oscar.meli.domain.model.product.ProductPlain
import com.oscar.meli.domain.model.product.toDomain
import com.oscar.meli.variables.Variables
import com.oscar.meli.variables.Variables.exampleProductPlainEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetFavoriteProductsUseCaseTest{

    @RelaxedMockK
    private lateinit var productRepository: ProductRepository

    lateinit var getFavoriteProductsUseCase: GetFavoriteProductsUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getFavoriteProductsUseCase = GetFavoriteProductsUseCase(productRepository)
    }

    @Test
    fun `when the database has items then return a list`() = runBlocking {
        //Given
        val listaProductos = exampleProductPlainEntity.map{it.toDomain()}

        coEvery{ productRepository.getFavoriteProducts()} returns flowOf(exampleProductPlainEntity)

        //When
        val response = getFavoriteProductsUseCase().first()
        coVerify(exactly = 1) { productRepository.getFavoriteProducts() }
        //Then
        assert(response == listaProductos)


    }

    @Test
    fun `when the database is empty then return an empty list`() = runBlocking {
        //Given
        val listaVacia = emptyList<ProductPlainEntity>()
        val listaMapeadaVacia = listaVacia.map{it.toDomain()}

        coEvery { productRepository.getFavoriteProducts() } returns flowOf(listaVacia)

        //When
        val response = getFavoriteProductsUseCase().first()
        coVerify(exactly = 1) { productRepository.getFavoriteProducts() }

        //Then
        assert(response == listaMapeadaVacia)

    }

}