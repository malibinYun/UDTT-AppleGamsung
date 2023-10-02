package com.udtt.applegamsung.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.udtt.applegamsung.data.dao.ApplePowersDao
import com.udtt.applegamsung.data.dao.AppleProductCategoriesDao
import com.udtt.applegamsung.data.dao.AppleProductsDao
import com.udtt.applegamsung.data.dao.TestResultsDao
import com.udtt.applegamsung.data.database.migration.RecreateEntitiesMigration
import com.udtt.applegamsung.data.entity.*
import com.udtt.applegamsung.data.util.DateTypeConverter

@TypeConverters(DateTypeConverter::class)
@Database(
    entities = [
        ApplePowerEntity::class,
        AppleProductEntity::class,
        AppleProductCategoryEntity::class,
        TestResultEntity::class,
    ],
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = RecreateEntitiesMigration::class)
    ],
    version = 4,
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun getApplePowersDao(): ApplePowersDao

    abstract fun getAppleProductsDao(): AppleProductsDao

    abstract fun getAppleProductCategoriesDao(): AppleProductCategoriesDao

    abstract fun getTestResultsDao(): TestResultsDao
}
