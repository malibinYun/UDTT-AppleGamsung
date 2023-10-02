package com.udtt.applegamsung.data.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


class AddingImageUrlColumnMigration : Migration(3, 4) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE AppleProductCategoryEntity ADD COLUMN imageUrl TEXT NOT NULL DEFAULT ''")
        database.execSQL("DELETE FROM AppleProductCategoryEntity")
    }
}
