package kim.dongun.paletteapp

import android.app.Application
import kim.dongun.paletteapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
//            androidLogger()

            // use the Android context given there
            androidContext(androidContext = this@GlobalApplication)

            // load properties from assets/koin.properties file
            androidFileProperties()

            // module list
//            modules(modules = appModule)
            modules(modules = viewModelModule)
        }
    }
}