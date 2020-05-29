package com.example.cookingbook

import android.app.Application
import com.example.cookingbook.di.netModules
import com.example.cookingbook.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(
                netModules,
                viewModelsModule
            ))
        }
    }
}