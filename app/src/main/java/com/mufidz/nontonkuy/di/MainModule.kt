package com.mufidz.nontonkuy.di

import com.mufidz.nontonkuy.data.DataManagerImpl
import com.mufidz.nontonkuy.data.DataManagerRepository
import com.mufidz.nontonkuy.data.RetrofitClient.apiServices
import com.mufidz.nontonkuy.data.RetrofitClient.provideMoshiConverter
import com.mufidz.nontonkuy.data.RetrofitClient.provideOkHttpClient
import com.mufidz.nontonkuy.ui.movie.*
import com.mufidz.nontonkuy.utils.DispatcherProvider
import com.mufidz.nontonkuy.utils.DispatcherProviderImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val repositoryModule = module {
    single<DataManagerRepository> { DataManagerImpl(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
}

private val useCaseModule = module {
    single { GetDiscoverMovie(get(), get()) }
    single { GetNowPlaying(get(), get()) }
}

private val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
}

private val networkModule = module {
    single { provideOkHttpClient() }
    single { provideMoshiConverter() }
}

private val dataModule = module {
    single { apiServices(get(), get()) }
}

private val dispatcherModule = module {
    single<DispatcherProvider> { DispatcherProviderImpl() }
}

val homeModules = listOf(
        networkModule,
        dataModule,
        repositoryModule,
        dispatcherModule,
        useCaseModule,
        viewModelModule
    )