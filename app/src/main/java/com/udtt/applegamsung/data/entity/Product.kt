package com.udtt.applegamsung.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties
import java.util.*

/**
 * Created By Yun Hyeok
 * on 3월 14, 2020
 */

@Entity
@IgnoreExtraProperties
data class Product(
    val name: String = "",
    val score: Int = 0,
    val categoryIndex: Int = 0,
    var categoryId: String = "",
    val imageUrl: String = "",

    @PrimaryKey
    val id: String = UUID.randomUUID().toString()
) {
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var imageByteArray: ByteArray? = null

    val categoryType: Category.Type
        get() = Category.Type.findByIndex(categoryIndex)
}