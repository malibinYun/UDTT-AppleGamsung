package com.udtt.applegamsung.data.database.migration

import androidx.room.DeleteColumn
import androidx.room.RenameColumn
import androidx.room.RenameTable
import androidx.room.migration.AutoMigrationSpec

@RenameTable(fromTableName = "TestResult", toTableName = "TestResultEntity")
@RenameTable(fromTableName = "Category", toTableName = "AppleProductCategoryEntity")

@RenameTable(fromTableName = "ApplePower", toTableName = "ApplePowerEntity")
@RenameColumn(tableName = "ApplePower", fromColumnName = "minPower", toColumnName = "minScore")
@RenameColumn(tableName = "ApplePower", fromColumnName = "maxPower", toColumnName = "maxScore")

@RenameTable(fromTableName = "Product", toTableName = "AppleProductEntity")
@DeleteColumn(tableName = "Product", columnName = "imageUrl")
@DeleteColumn(tableName = "Product", columnName = "imageByteArray")
@RenameColumn(tableName = "Product", fromColumnName = "categoryId", toColumnName = "appleProductCategoryId")
@RenameColumn(tableName = "Product", fromColumnName = "categoryIndex", toColumnName = "sortPriority")
class RecreateEntitiesMigration : AutoMigrationSpec
