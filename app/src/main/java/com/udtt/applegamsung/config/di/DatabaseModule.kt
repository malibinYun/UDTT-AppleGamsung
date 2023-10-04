package com.udtt.applegamsung.config.di

import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.udtt.applegamsung.data.database.AppDatabase
import com.udtt.applegamsung.data.database.migration.AddingImageUrlColumnMigration
import com.udtt.applegamsung.data.database.migration.AppleProductEntityMigration
import com.udtt.applegamsung.data.repository.DefaultAppleBoxItemsRepository
import com.udtt.applegamsung.data.repository.DefaultCategoriesRepository
import com.udtt.applegamsung.data.repository.DefaultProductsRepository
import com.udtt.applegamsung.data.repository.DefaultTestResultsRepository
import com.udtt.applegamsung.data.repository.UserIdentifyRepository
import com.udtt.applegamsung.data.source.local.LocalAppleBoxItemsDataSource
import com.udtt.applegamsung.data.source.local.LocalCategoriesDataSource
import com.udtt.applegamsung.data.source.local.LocalProductsDataSource
import com.udtt.applegamsung.data.source.local.LocalTestResultsDataSource
import com.udtt.applegamsung.data.source.remote.RemoteCategoriesDataSource
import com.udtt.applegamsung.data.source.remote.RemoteProductsDataSource
import com.udtt.applegamsung.data.source.remote.RemoteTestResultsDataSource
import com.udtt.applegamsung.domain.repository.AppleBoxItemsRepository
import com.udtt.applegamsung.domain.repository.CategoriesRepository
import com.udtt.applegamsung.domain.repository.ProductsRepository
import com.udtt.applegamsung.domain.repository.TestResultsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

val appDataBaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database")
            .addMigrations(AppleProductEntityMigration(), AddingImageUrlColumnMigration())
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
        LocalProductsDataSource(
            get<AppDatabase>().getAppleProductsDao(),
        )
    }
    single {
        LocalTestResultsDataSource(
            get<AppDatabase>().getTestResultsDao(),
            get<AppDatabase>().getApplePowersDao(),
        )
    }
    single {
        LocalAppleBoxItemsDataSource(
            get<AppDatabase>().getAppleProductsDao(),
        )
    }
}

val firestoreModule = module {
    single { FirebaseFirestore.getInstance() }
}

val remoteDataSourceModule = module {
    single { RemoteCategoriesDataSource(get()) }
    single { RemoteProductsDataSource(get()) }
    single { RemoteTestResultsDataSource(get()) }
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
            get<RemoteProductsDataSource>(),
            get<LocalProductsDataSource>(),
            get<LocalAppleBoxItemsDataSource>()
        )
    }
    single<TestResultsRepository> {
        DefaultTestResultsRepository(
            get<RemoteTestResultsDataSource>(),
            get<LocalTestResultsDataSource>()
        )
    }
    single<AppleBoxItemsRepository> {
        DefaultAppleBoxItemsRepository(
            get<LocalAppleBoxItemsDataSource>()
        )
    }
}
