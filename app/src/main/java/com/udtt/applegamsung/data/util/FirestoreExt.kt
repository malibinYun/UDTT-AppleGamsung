package com.udtt.applegamsung.data.util

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.udtt.applegamsung.data.entity.Category

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

fun FirebaseFirestore.getCollection(collectionPath: String): Task<QuerySnapshot> {
    return this.collection(collectionPath).get()
}

fun QuerySnapshot.toCategories(): List<Category> {
    return this.documents.map {
        it.toObject(Category::class.java) ?: throw RuntimeException("객체를 변환할 수 없음")
    }
}