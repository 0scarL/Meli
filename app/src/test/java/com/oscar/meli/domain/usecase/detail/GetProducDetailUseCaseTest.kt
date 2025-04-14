package com.oscar.meli.domain.usecase.detail

import com.oscar.meli.data.repository.detail.DetailRepository
import com.oscar.meli.domain.model.detalle.DetailPlain
import com.oscar.meli.variables.Variables
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.fail
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetProducDetailUseCaseTest {

    @RelaxedMockK
    private lateinit var detailRepository: DetailRepository

    lateinit var getProducDetailUseCase: GetProducDetailUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getProducDetailUseCase = GetProducDetailUseCase(detailRepository)

    }

    @Test
    fun `when the product is favorite then return a favorite detail`() = runBlocking {
        //Given
        val exampleDetailFav = Variables.exampleDetailFav

        val token = "token"
        val productId = "12345"
        val isfavorite = true


        coEvery {
            detailRepository.getProductDetail(
                token,
                productId,
                isfavorite
            )
        } returns exampleDetailFav


        //When
        val response = getProducDetailUseCase(token, productId, isfavorite)

        //Then
        assert(response == exampleDetailFav)
    }

    @Test
    fun `when the product is not favorite then return a product no favorite`() = runBlocking {
        //Given
        val exampleDetailNoFav = Variables.exampleDetailNoFav

        val token = "token"
        val productId = "123456"
        val isNotfavorite = false

        //When
        coEvery {
            detailRepository.getProductDetail(
                token,
                productId,
                isNotfavorite
            )
        } returns exampleDetailNoFav

        //Then
        val response = getProducDetailUseCase(token, productId, isNotfavorite)
        coVerify(exactly = 1) { detailRepository.getProductDetail(token, productId, isNotfavorite) }

        assert(response == exampleDetailNoFav)

    }



}









