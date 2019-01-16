package maystruks08.gmail.com.romantic

import android.app.Application
import maystruks08.gmail.com.romantic.core.di.hike.HikeComponent
import maystruks08.gmail.com.romantic.core.di.application.AndroidModule
import maystruks08.gmail.com.romantic.core.di.application.AppComponent
import maystruks08.gmail.com.romantic.core.di.application.DaggerAppComponent


class App: Application() {

    companion object {

        lateinit var appComponent: AppComponent

        var operationComponent: HikeComponent? = null
            get () {
                if (field == null)
                    field = appComponent.hikeComponent()
                return field
            }
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .androidModule(AndroidModule(this))
            .build()

    }

}