package com.udtt.applegamsung.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.udtt.applegamsung.data.entity.SelectedProduct

@Dao
interface SelectedProductsDao {

    @Query("SELECT * FROM selectedproduct")
    fun getSelectedProducts(): List<SelectedProduct>

    @Insert
    fun insertSelectedProducts(selectedProducts: List<SelectedProduct>)

    @Delete
    fun deleteSelectedProduct(selectedProduct: SelectedProduct)
}