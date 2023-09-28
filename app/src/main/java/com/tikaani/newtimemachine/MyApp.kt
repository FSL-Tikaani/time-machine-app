package com.tikaani.newtimemachine

import android.app.Application
import com.tikaani.newtimemachine.data.domain.api.ApiImpl

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        api = ApiImpl()
    }

    companion object {
        lateinit var api: ApiImpl
            private set
    }
}