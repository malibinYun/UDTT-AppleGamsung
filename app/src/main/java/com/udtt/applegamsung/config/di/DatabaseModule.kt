package com.udtt.applegamsung.config.di

import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.udtt.applegamsung.data.AppDatabase
import com.udtt.applegamsung.data.repository.CategoriesRepository
import com.udtt.applegamsung.data.repository.DeviceIdRepository
import com.udtt.applegamsung.data.repository.ProductsRepository
import com.udtt.applegamsung.data.source.local.CategoriesLocalDataSource
import com.udtt.applegamsung.data.source.local.ProductsLocalDataSource
import com.udtt.applegamsung.data.source.remote.CategoriesRemoteDataSource
import com.udtt.applegamsung.data.source.remote.ProductsRemoteDataSource
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
}

val remoteDataSourceModule = module {
    single { CategoriesRemoteDataSource(FirebaseFirestore.getInstance()) }
    single { ProductsRemoteDataSource(FirebaseFirestore.getInstance()) }
}

val repositoryModule = module {
    single { DeviceIdRepository(androidContext()) }
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
}

