package com.oscar.meli.domain.usecase.detail

import com.oscar.meli.data.repository.detail.DetailRepository
import com.oscar.meli.domain.model.detalle.DetailPlain
import com.oscar.meli.domain.model.detalle.toPlainDomain
import com.oscar.meli.ui.model.detail.toVm
import javax.inject.Inject

class GetProducDetailUseCase @Inject constructor(
 private val detailRepository: DetailRepository
) {
    suspend operator fun invoke(token: String, productId: String, isfavorite: Boolean): DetailPlain {
        return detailRepository.getProductDetail(token, productId, isfavorite)

    }

}