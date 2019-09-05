package com.inaki.tmobilecodechallenge

import android.app.Application
import com.inaki.tmobilecodechallenge.DependencyInjection.fragmentModule
import com.inaki.tmobilecodechallenge.DependencyInjection.networkModule
import com.inaki.tmobilecodechallenge.DependencyInjection.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ReposApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ReposApp)
            modules(listOf(networkModule, viewModelModule, fragmentModule))
        }
    }
}