package com.udtt.applegamsung.config.di

import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.config.ViewModelFactory
import com.udtt.applegamsung.util.AsyncExecutor
import org.koin.dsl.module

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

val asyncExecutorModule = module {
    single { AsyncExecutor() }
}

val viewModelFactoryModule = module {
    single<ViewModelProvider.Factory> { ViewModelFactory(get(), get()) }
}

val diModules = listOf(
    asyncExecutorModule,
    viewModelFactoryModule,
    appDataBaseModule,
    localDataSourceModule,
    remoteDataSourceModule,
    repositoryModule
)