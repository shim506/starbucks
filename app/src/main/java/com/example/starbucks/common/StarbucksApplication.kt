package com.example.starbucks.common

import android.app.Application
import com.example.starbucks.data.repository.repositoryModule
import com.example.starbucks.network.mainNetworkModule
import com.example.starbucks.network.starbucksNetworkModule
import com.example.starbucks.ui.home.homeViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StarbucksApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@StarbucksApplication)
            modules(
                homeViewModelModule, mainNetworkModule,
                starbucksNetworkModule, repositoryModule
            )
        }
    }
}