package com.udtt.applegamsung.data.database.migration

import androidx.room.testing.MigrationTestHelper
import androidx.room.util.useCursor
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.udtt.applegamsung.data.database.AppDatabase
import com.udtt.applegamsung.data.database.cursor.getIntColumnOf
import com.udtt.applegamsung.data.database.cursor.getLongColumnOf
import com.udtt.applegamsung.data.database.cursor.getStringColumnOf
import org.junit.Rule
import org.junit.Test

internal class RecreateEntitiesMigrationTest {

    companion object {
        private const val TestDbName = "TESTDB"
        private val AutoMigration1to2 = RecreateEntitiesMigration()
    }

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        AppDatabase::class.java,
        listOf(AutoMigration1to2),
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    fun validateMigrationVersion1To2() {
        // given
        helper.createDatabase(TestDbName, 1).apply {
            execSQL("INSERT INTO TestResult VALUES ('DEVICEID', 'NICKNAME', 100, 1690033675562, 'ID')")
            execSQL("INSERT INTO Category VALUES ('NAME', '1', 'ID')")
            execSQL("INSERT INTO ApplePower VALUES ('NAME', 'DESCRIPTION', 100, 200, 'ID')")
            execSQL("INSERT INTO Product VALUES ('NAME', 300, 3, 'CATEGORYID', 'IMAGEURL', 'ID', NULL)")

            close()
        }

        // when
        val migratedDb = helper.runMigrationsAndValidate(TestDbName, 2, true)

        // then
        validateTestResultTableMigration(migratedDb)
        validateCategoryTableMigration(migratedDb)
        validateApplePowerTableMigration(migratedDb)
        validateProductTableMigration(migratedDb)
    }

    private fun validateTestResultTableMigration(db: SupportSQLiteDatabase) {
        val queryResult = runCatching { db.query("SELECT * FROM TestResult") }
        assertThat(queryResult.isFailure).isTrue()

        db.query("SELECT * FROM TestResultEntity")
            .also { it.moveToNext() }
            .useCursor { cursor ->
                assertThat(cursor.getStringColumnOf("deviceId")).isEqualTo("DEVICEID")
                assertThat(cursor.getStringColumnOf("nickname")).isEqualTo("NICKNAME")
                assertThat(cursor.getIntColumnOf("totalScore")).isEqualTo(100)
                assertThat(cursor.getLongColumnOf("timeStamp")).isEqualTo(1690033675562L)
                assertThat(cursor.getStringColumnOf("id")).isEqualTo("ID")
            }
    }

    private fun validateCategoryTableMigration(db: SupportSQLiteDatabase) {
        val queryResult = runCatching { db.query("SELECT * FROM Category") }
        assertThat(queryResult.isFailure).isTrue()

        db.query("SELECT * FROM AppleProductCategoryEntity")
            .also { it.moveToNext() }
            .useCursor { cursor ->
                assertThat(cursor.getStringColumnOf("name")).isEqualTo("NAME")
                assertThat(cursor.getIntColumnOf("index")).isEqualTo(1)
                assertThat(cursor.getStringColumnOf("id")).isEqualTo("ID")
            }
    }

    private fun validateApplePowerTableMigration(db: SupportSQLiteDatabase) {
        val queryResult = runCatching { db.query("SELECT * FROM ApplePower") }
        assertThat(queryResult.isFailure).isTrue()

        db.query("SELECT * FROM ApplePowerEntity")
            .also { it.moveToNext() }
            .useCursor { cursor ->
                assertThat(cursor.getColumnIndex("minPower")).isEqualTo(-1)
                assertThat(cursor.getColumnIndex("maxPower")).isEqualTo(-1)

                assertThat(cursor.getStringColumnOf("name")).isEqualTo("NAME")
                assertThat(cursor.getStringColumnOf("description")).isEqualTo("DESCRIPTION")
                assertThat(cursor.getIntColumnOf("minScore")).isEqualTo(100)
                assertThat(cursor.getIntColumnOf("maxScore")).isEqualTo(200)
                assertThat(cursor.getStringColumnOf("id")).isEqualTo("ID")
            }
    }

    private fun validateProductTableMigration(db: SupportSQLiteDatabase) {
        val queryResult = runCatching { db.query("SELECT * FROM Product") }
        assertThat(queryResult.isFailure).isTrue()

        db.query("SELECT * FROM AppleProductEntity")
            .also { it.moveToNext() }
            .useCursor { cursor ->
                assertThat(cursor.getColumnIndex("imageUrl")).isEqualTo(-1)
                assertThat(cursor.getColumnIndex("imageByteArray")).isEqualTo(-1)
                assertThat(cursor.getColumnIndex("categoryId")).isEqualTo(-1)
                assertThat(cursor.getColumnIndex("categoryIndex")).isEqualTo(-1)

                assertThat(cursor.getStringColumnOf("name")).isEqualTo("NAME")
                assertThat(cursor.getIntColumnOf("score")).isEqualTo(300)
                assertThat(cursor.getIntColumnOf("sortPriority")).isEqualTo(3)
                assertThat(cursor.getStringColumnOf("appleProductCategoryId")).isEqualTo("CATEGORYID")
                assertThat(cursor.getStringColumnOf("id")).isEqualTo("ID")
            }
    }
}
