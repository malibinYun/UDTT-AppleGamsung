package com.udtt.applegamsung.data.source

import com.udtt.applegamsung.data.entity.Product

/**
 * Created By Yun Hyeok
 * on 3월 16, 2020
 */

interface ProductsDataSource {

    fun getProducts(categoryIndex: Int, callback: (products: List<Product>) -> Unit)

    fun saveProducts(products: List<Product>)

}