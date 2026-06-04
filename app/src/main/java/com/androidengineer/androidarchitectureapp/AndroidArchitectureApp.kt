package com.androidengineer.androidarchitectureapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidArchitectureApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AndroidArchitectureApp)
            modules(appModule)
        }
    }
}