package com.udtt.applegamsung.data.remote.firestore

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.tasks.await

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

suspend fun CollectionReference.getDocumentSnapshots(): List<DocumentSnapshot> {
    return this.get().await().documents
}

suspend fun CollectionReference.getDocumentSnapshots2(): Result<List<DocumentSnapshot>> {
    return runCatching { this.get().await() }
        .map { it.documents }
}

suspend fun CollectionReference.addAwait(data: Any): Result<Unit> {
    return runCatching { this.add(data).await() }
}
