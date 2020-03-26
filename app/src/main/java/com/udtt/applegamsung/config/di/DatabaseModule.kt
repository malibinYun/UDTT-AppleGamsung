package com.udtt.applegamsung.config.di

import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.udtt.applegamsung.data.AppDatabase
import com.udtt.applegamsung.data.repository.*
import com.udtt.applegamsung.data.source.local.CategoriesLocalDataSource
import com.udtt.applegamsung.data.source.local.ProductsLocalDataSource
import com.udtt.applegamsung.data.source.local.AppleBoxItemsLocalDataSource
import com.udtt.applegamsung.data.source.local.TestResultsLocalDataSource
import com.udtt.applegamsung.data.source.remote.CategoriesRemoteDataSource
import com.udtt.applegamsung.data.source.remote.ProductsRemoteDataSource
import com.udtt.applegamsung.data.source.remote.TestResultsRemoteDataSource
import org.koin.android.ext.koin.androidContext
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
    single { CategoriesLocalDataSource(get(), get<AppDatabase>().categoriesDao()) }
    single { ProductsLocalDataSource(get(), get<AppDatabase>().productsDao()) }
    single { TestResultsLocalDataSource(get(), get<AppDatabase>().testResultsDao()) }
    single { AppleBoxItemsLocalDataSource(get(), get<AppDatabase>().appleBoxItemsDao()) }
}

val firestoreModule = module {
    single { FirebaseFirestore.getInstance() }
}

val remoteDataSourceModule = module {
    single { CategoriesRemoteDataSource(get()) }
    single { ProductsRemoteDataSource(get()) }
    single { TestResultsRemoteDataSource(get()) }
}

val repositoryModule = module {
    single { UserIdentifyRepository(androidContext()) }
    single {
        CategoriesRepository(
            get<CategoriesRemoteDataSource>(),
            get<CategoriesLocalDataSource>()
        )
    }
    single {
        ProductsRepository(
            get<ProductsRemoteDataSource>(),
            get<ProductsLocalDataSource>()
        )
    }
    single {
        TestResultsRepository(
            get<TestResultsRemoteDataSource>(),
            get<TestResultsLocalDataSource>()
        )
    }
    single {
        AppleBoxItemsRepository(
            get<AppleBoxItemsLocalDataSource>()
        )
    }
}

