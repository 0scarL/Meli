package com.oscar.meli.domain.usecase.detail

import com.oscar.meli.data.model.db.toEntity
import com.oscar.meli.data.repository.detail.DetailRepository
import com.oscar.meli.variables.Variables.exampleForInsert
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class InsertDetailUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: DetailRepository

    lateinit var insertDetailUseCase: InsertDetailUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        insertDetailUseCase = InsertDetailUseCase(repository)
    }

    @Test
    fun `when the correct data is passed insert is called and item is inserted in the database `() =
        runBlocking {

        //Given
        val itemDetail = exampleForInsert

        //When
        insertDetailUseCase(itemDetail)

        //then
        coVerify(exactly = 1) { repository.insertDetail(itemDetail.toEntity()) }
    }

}


