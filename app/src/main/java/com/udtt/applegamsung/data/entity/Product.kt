package com.udtt.applegamsung.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.google.firebase.firestore.IgnoreExtraProperties

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
    val imageUrl: String = ""
) {
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var imageByteArray: ByteArray? = null

    @Ignore
    val category: Category.Type = Category.Type.findByIndex(categoryIndex)
}