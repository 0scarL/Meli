package com.oscar.meli.data.repository.product

import com.oscar.meli.data.datasource.products.ProductDataSource
import com.oscar.meli.data.db.ProductDao
import com.oscar.meli.data.db.ProductPlainEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDataSource: ProductDataSource,
    private val productDao: ProductDao

) : ProductRepository {

    override suspend fun getProducts(token: String, status: String, siteId: String, query: String) =
        withContext(Dispatchers.IO) { productDataSource.getProducts(token, status, siteId, query)}

    override suspend fun insertProducts(products: List<ProductPlainEntity>) {
        withContext(Dispatchers.IO){productDao.insertAllProducts(products)}
    }


}