package com.udtt.applegamsung.config.di

import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.udtt.applegamsung.data.database.AppDatabase
import com.udtt.applegamsung.data.database.migration.AppleProductEntityMigration
import com.udtt.applegamsung.data.repository.DefaultAppleBoxItemsRepository
import com.udtt.applegamsung.data.repository.DefaultCategoriesRepository
import com.udtt.applegamsung.data.repository.DefaultProductsRepository
import com.udtt.applegamsung.data.repository.TestResultsRepository
import com.udtt.applegamsung.data.repository.UserIdentifyRepository
import com.udtt.applegamsung.data.source.local.AppleBoxItemsLocalDataSource
import com.udtt.applegamsung.data.source.local.CategoriesLocalDataSource
import com.udtt.applegamsung.data.source.local.ProductsLocalDataSource
import com.udtt.applegamsung.data.source.local.TestResultsLocalDataSource
import com.udtt.applegamsung.data.source.remote.CategoriesRemoteDataSource
import com.udtt.applegamsung.data.source.remote.ProductsRemoteDataSource
import com.udtt.applegamsung.data.source.remote.TestResultsRemoteDataSource
import com.udtt.applegamsung.domain.repository.AppleBoxItemsRepository
import com.udtt.applegamsung.domain.repository.CategoriesRepository
import com.udtt.applegamsung.domain.repository.ProductsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

val MainCoroutineScope = CoroutineScope(Dispatchers.Main)

val IoCoroutineScope = CoroutineScope(Dispatchers.IO)

val appDataBaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database")
            .addMigrations(AppleProductEntityMigration())
            .build()
    }
}

val localDataSourceModule = module {
    single {
        CategoriesLocalDataSource(
            get<AppDatabase>().getAppleProductCategoriesDao(),
            MainCoroutineScope,
            IoCoroutineScope,
        )
    }
    single {
        ProductsLocalDataSource(
            get<AppDatabase>().getAppleProductsDao(),
            MainCoroutineScope,
            IoCoroutineScope,
        )
    }
    single {
        TestResultsLocalDataSource(
            get<AppDatabase>().getTestResultsDao(),
            get<AppDatabase>().getApplePowersDao(),
            MainCoroutineScope,
            IoCoroutineScope,
        )
    }
    single {
        AppleBoxItemsLocalDataSource(
            get<AppDatabase>().getAppleProductsDao(),
            MainCoroutineScope,
            IoCoroutineScope,
        )
    }
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
    single<CategoriesRepository> {
        DefaultCategoriesRepository(
            get<CategoriesRemoteDataSource>(),
            get<CategoriesLocalDataSource>()
        )
    }
    single<ProductsRepository> {
        DefaultProductsRepository(
            get<ProductsRemoteDataSource>(),
            get<ProductsLocalDataSource>(),
            get<AppleBoxItemsLocalDataSource>()
        )
    }
    single {
        TestResultsRepository(
            get<TestResultsRemoteDataSource>(),
            get<TestResultsLocalDataSource>()
        )
    }
    single<AppleBoxItemsRepository> {
        DefaultAppleBoxItemsRepository(
            get<AppleBoxItemsLocalDataSource>()
        )
    }
}
