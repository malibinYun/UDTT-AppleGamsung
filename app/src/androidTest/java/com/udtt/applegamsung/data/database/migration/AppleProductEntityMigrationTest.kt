package com.udtt.applegamsung.data.database.migration

import androidx.room.testing.MigrationTestHelper
import androidx.room.util.useCursor
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.udtt.applegamsung.data.database.AppDatabase
import com.udtt.applegamsung.data.database.cursor.getBooleanColumnOf
import com.udtt.applegamsung.data.database.cursor.getStringColumnOf
import org.junit.Rule
import org.junit.Test

class AppleProductEntityMigrationTest {

    companion object {
        private val TestDbName = "TESTDB"
        private val Migration2to3 = AppleProductEntityMigration()
    }

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        AppDatabase::class.java,
        emptyList(),
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    fun validateMigrationVersion2To3() {
        // given

        // 이전 버전 디비에 iphone과 imac 제품을 선택 했음. (상자에 담았음)
        // iphone엔 애캐플 체크 X
        // imac엔 애캐플 체크 O

        // 현 버전 디비에는 아래 3개의 제품 만이 디비에 있음
        // iphone, imac, airpods

        helper.createDatabase(TestDbName, 2).apply {
            execSQL("INSERT INTO AppleBoxItem VALUES (0, 'ID1', 'iphone', 100, 1, 'phoneType', 'URL', 'iphoneID', NULL)")
            execSQL("INSERT INTO AppleBoxItem VALUES (1, 'ID2', 'imac', 300, 3, 'labtopType', 'URL', 'imacID', NULL)")

            execSQL("INSERT INTO AppleProductEntity VALUES ('iphone', 'phoneType', 100, 1, 0, 0, 'iphoneID')")
            execSQL("INSERT INTO AppleProductEntity VALUES ('imac', 'labtopType', 300, 3, 0, 0, 'imacID')")
            execSQL("INSERT INTO AppleProductEntity VALUES ('airpods', 'airpodsType', 500, 5, 0, 0, 'airpodsID')")

            close()
        }

        // when
        val migratedDb = helper.runMigrationsAndValidate(TestDbName, 3, true, Migration2to3)

        // then

        // 기존 선택한 제품을 나타내는 테이블인 AppleBoxItem는 삭제 되어야 함.

        // 현 버전 디비의 제품(AppleProductEntity) Row에
        // 선택 여부 (상자에 담은 여부) 및 애캐플 체크 여부가 갱신 되어야 함.

        val queryResult = runCatching { migratedDb.query("SELECT * FROM AppleBoxItem") }
        assertThat(queryResult.isFailure).isTrue()

        migratedDb.query("SELECT * FROM AppleProductEntity ORDER BY score ASC")
            .also { it.moveToNext() }
            .useCursor { cursor ->
                // iphone isInBox == true && hasAppleCare == false
                assertThat(cursor.getStringColumnOf("name")).isEqualTo("iphone")
                assertThat(cursor.getBooleanColumnOf("isInBox")).isTrue()
                assertThat(cursor.getBooleanColumnOf("hasAppleCare")).isFalse()

                // imac isInBox == true && hasAppleCare == true
                cursor.moveToNext()
                assertThat(cursor.getStringColumnOf("name")).isEqualTo("imac")
                assertThat(cursor.getBooleanColumnOf("isInBox")).isTrue()
                assertThat(cursor.getBooleanColumnOf("hasAppleCare")).isTrue()

                // airpods isInBox == false && hasAppleCare == false
                cursor.moveToNext()
                assertThat(cursor.getStringColumnOf("name")).isEqualTo("airpods")
                assertThat(cursor.getBooleanColumnOf("isInBox")).isFalse()
                assertThat(cursor.getBooleanColumnOf("hasAppleCare")).isFalse()
            }
    }
}
