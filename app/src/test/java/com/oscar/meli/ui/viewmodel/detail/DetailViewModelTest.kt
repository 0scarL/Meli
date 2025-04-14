package com.oscar.meli.ui.viewmodel.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.oscar.meli.domain.usecase.detail.GetProducDetailUseCase
import com.oscar.meli.domain.usecase.detail.InsertDetailUseCase
import com.oscar.meli.domain.usecase.product.InsertProductUseCase
import com.oscar.meli.ui.model.detail.toVm
import com.oscar.meli.ui.model.states.DetailUiState
import com.oscar.meli.variables.Variables.exampleDetail
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailViewModelTest{

    @RelaxedMockK
    private lateinit var getProductDetailUseCase: GetProducDetailUseCase

    @RelaxedMockK
    private lateinit var insertDetailUseCase: InsertDetailUseCase

    @RelaxedMockK
    private lateinit var insertProductUseCase: InsertProductUseCase

    private lateinit var detailViewModel : DetailViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule() //definido para livedata


    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        detailViewModel = DetailViewModel(insertProductUseCase,getProductDetailUseCase,insertDetailUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)

    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when getProductDetailUseCase return a DetailPlain`()= runTest {
        //Given
        val id = "001"
        val token = "token"
        val isfavorite = true
        val detail = exampleDetail

        coEvery { getProductDetailUseCase(token, id,isfavorite) } returns detail

        //When
        detailViewModel.getProductDetail(id,isfavorite)

        //Then
        assert(detailViewModel.uiStateDetail.value is DetailUiState.Success)

    }


}