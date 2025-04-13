package com.oscar.meli.domain.usecase.detail

import com.oscar.meli.data.model.db.toEntity
import com.oscar.meli.data.repository.detail.DetailRepository
import com.oscar.meli.domain.model.detalle.DetailPlain
import javax.inject.Inject

class InsertDetailUseCase @Inject constructor(
    private val repository: DetailRepository
) {

    suspend operator fun invoke(detail: DetailPlain) {
        repository.insertDetail(detail.toEntity())
    }


}