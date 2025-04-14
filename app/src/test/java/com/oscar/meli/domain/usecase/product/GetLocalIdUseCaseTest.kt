package com.oscar.meli.domain.usecase.product

import com.oscar.meli.data.repository.product.ProductRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetLocalIdUseCaseTest{

    @RelaxedMockK
    private lateinit var productRepository: ProductRepository

    lateinit var getLocalIdUseCase: GetLocalIdUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getLocalIdUseCase = GetLocalIdUseCase(productRepository)
    }

    @Test
    fun `when the database has items then return a list`() =  runBlocking{
        //Given
        val listaId = listOf("123","1234","12345")
        coEvery { productRepository.getLocalId() } returns flowOf(listaId)

        //When
        val response = getLocalIdUseCase().first()

        //Then
        assert(response == listaId)

    }

    @Test
    fun `when the database is empty then return an empty list`() =  runBlocking {
        //Given
        val listaVacia = emptyList<String>()
        coEvery { productRepository.getLocalId() } returns flowOf(listaVacia)

        //When
        val response = getLocalIdUseCase().first()

        //Then
        assert(response == listaVacia)

    }


}