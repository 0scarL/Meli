package com.oscar.meli.domain.usecase

import com.oscar.meli.data.repository.product.ProductRepository
import com.oscar.meli.domain.model.product.toDomain
import com.oscar.meli.ui.model.ProductPlainVm
import com.oscar.meli.ui.model.toVmList
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
   private val repository: ProductRepository) {

   suspend operator fun invoke(token: String, status: String, siteId: String, query: String): List<ProductPlainVm> {
      val productResult = repository.getProducts(token, status, siteId, query).toDomain()
      return productResult.results.toVmList()



   }
}