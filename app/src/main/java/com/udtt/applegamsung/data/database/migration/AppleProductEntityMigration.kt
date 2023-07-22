package com.udtt.applegamsung.data.database.migration

import android.database.Cursor
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class AppleProductEntityMigration : Migration(2, 3) {

    override fun migrate(database: SupportSQLiteDatabase) {
        val legacyAppleBoxItemsCursor = database.query("SELECT * FROM AppleBoxItem")

        val selectedProducts = mutableListOf<SelectedProduct>()

        while (legacyAppleBoxItemsCursor.moveToNext()) {
            selectedProducts.add(
                SelectedProduct(
                    productId = legacyAppleBoxItemsCursor.getStringColumnOf("id"),
                    hasAppleCare = legacyAppleBoxItemsCursor.getBooleanColumnOf("hasAppleCare"),
                )
            )
        }
        legacyAppleBoxItemsCursor.close()

        val selectedProductIds = selectedProducts.map { it.productId }.joinToString { "\'$it\'" }
        if (selectedProductIds.isNotEmpty()) {
            database.execSQL("UPDATE AppleProductEntity SET isInBox = 1 WHERE id IN ($selectedProductIds)")
        }

        val hasAppleCareProductIds = selectedProducts
            .filter { it.hasAppleCare }
            .map { it.productId }
            .joinToString { "\'$it\'" }
        if (hasAppleCareProductIds.isNotEmpty()) {
            database.execSQL("UPDATE AppleProductEntity SET hasAppleCare = 1 WHERE id IN ($hasAppleCareProductIds)")
        }

        database.execSQL("DROP TABLE AppleBoxItem")
    }

    private fun Cursor.getStringColumnOf(columnName: String): String {
        val columnIndex = this.getColumnIndexOrThrow(columnName)
        return this.getString(columnIndex)
    }

    // 0 - false, 1 - true
    private fun Cursor.getBooleanColumnOf(columnName: String): Boolean {
        val columnIndex = this.getColumnIndexOrThrow(columnName)
        return this.getInt(columnIndex) == 1
    }

    private data class SelectedProduct(
        val productId: String,
        val hasAppleCare: Boolean,
    )
}
