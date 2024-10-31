package com.plcoding.cryptotracker

import android.app.Application
import com.plcoding.cryptotracker.di.appModule
import io.ktor.http.ContentType
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class CryptoTrackerApp:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CryptoTrackerApp)
            androidLogger()

            modules(appModule)

        }
    }
}