package com.udtt.applegamsung.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.udtt.applegamsung.data.dao.CategoriesDao
import com.udtt.applegamsung.data.dao.ProductsDao
import com.udtt.applegamsung.data.dao.TestResultsDao
import com.udtt.applegamsung.data.entity.Category
import com.udtt.applegamsung.data.entity.Product
import com.udtt.applegamsung.data.entity.TestResult

@Database(entities = [Category::class, Product::class, TestResult::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoriesDao(): CategoriesDao

    abstract fun productsDao(): ProductsDao

    abstract fun testResultsDao(): TestResultsDao

}