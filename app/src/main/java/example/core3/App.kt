package example.core3

import android.app.Application
import example.core3.di.DomainModule
import example.core3.di.NetworkModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        startKoin(this, listOf(NetworkModule.module, DomainModule.module))
    }
}