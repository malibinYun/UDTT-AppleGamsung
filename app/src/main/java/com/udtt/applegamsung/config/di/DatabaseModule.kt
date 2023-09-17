package com.udtt.applegamsung.config.di

import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.udtt.applegamsung.data.database.AppDatabase
import com.udtt.applegamsung.data.database.migration.AppleProductEntityMigration
import com.udtt.applegamsung.data.repository.DefaultAppleBoxItemsRepository
import com.udtt.applegamsung.data.repository.DefaultCategoriesRepository
import com.udtt.applegamsung.data.repository.DefaultProductsRepository
import com.udtt.applegamsung.data.repository.DefaultTestResultsRepository
import com.udtt.applegamsung.data.repository.UserIdentifyRepository
import com.udtt.applegamsung.data.source.local.AppleBoxItemsLocalDataSource
import com.udtt.applegamsung.data.source.local.LocalCategoriesDataSource
import com.udtt.applegamsung.data.source.local.ProductsLocalDataSource
import com.udtt.applegamsung.data.source.local.TestResultsLocalDataSource
import com.udtt.applegamsung.data.source.remote.RemoteCategoriesDataSource
import com.udtt.applegamsung.data.source.remote.ProductsRemoteDataSource
import com.udtt.applegamsung.data.source.remote.TestResultsRemoteDataSource
import com.udtt.applegamsung.domain.repository.AppleBoxItemsRepository
import com.udtt.applegamsung.domain.repository.CategoriesRepository
import com.udtt.applegamsung.domain.repository.ProductsRepository
import com.udtt.applegamsung.domain.repository.TestResultsRepository
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
        LocalCategoriesDataSource(
            get<AppDatabase>().getAppleProductCategoriesDao(),
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
    single { RemoteCategoriesDataSource(get()) }
    single { ProductsRemoteDataSource(get()) }
    single { TestResultsRemoteDataSource(get()) }
}

val repositoryModule = module {
    single { UserIdentifyRepository(androidContext()) }
    single<CategoriesRepository> {
        DefaultCategoriesRepository(
            get<RemoteCategoriesDataSource>(),
            get<LocalCategoriesDataSource>()
        )
    }
    single<ProductsRepository> {
        DefaultProductsRepository(
            get<ProductsRemoteDataSource>(),
            get<ProductsLocalDataSource>(),
            get<AppleBoxItemsLocalDataSource>()
        )
    }
    single<TestResultsRepository> {
        DefaultTestResultsRepository(
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
