package com.androidengineer.androidarchitectureapp

import android.app.Application
import com.androidengineer.core.data.coreModule
import com.androidengineer.feature.postViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidArchitectureApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AndroidArchitectureApp)
            modules(coreModule.apply {
                add(postViewModelModule)
            })
        }
    }
}