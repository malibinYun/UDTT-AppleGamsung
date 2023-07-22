package com.udtt.applegamsung.data.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.udtt.applegamsung.data.database.cursor.getBooleanColumnOf
import com.udtt.applegamsung.data.database.cursor.getStringColumnOf

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

    private data class SelectedProduct(
        val productId: String,
        val hasAppleCare: Boolean,
    )
}
