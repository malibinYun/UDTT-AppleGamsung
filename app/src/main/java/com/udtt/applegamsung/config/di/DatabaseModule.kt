package com.udtt.applegamsung.config.di

import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.udtt.applegamsung.data.AppDatabase
import com.udtt.applegamsung.data.repository.CategoriesRepository
import com.udtt.applegamsung.data.source.CategoriesDataSource
import com.udtt.applegamsung.data.source.local.CategoriesLocalDataSource
import com.udtt.applegamsung.data.source.remote.CategoriesRemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

val appDataBaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database").build()
    }
}

val localDataSourceModule = module {
    single<CategoriesDataSource>(named("local")) {
        CategoriesLocalDataSource(get(), get<AppDatabase>().categoriesDao())
    }
}

val remoteDataSourceModule = module {
    single<CategoriesDataSource>(named("remote")) {
        CategoriesRemoteDataSource(FirebaseFirestore.getInstance())
    }
}

val repositoryModule = module {
    single { CategoriesRepository(get(named("remote")), get(named("local"))) }
}

