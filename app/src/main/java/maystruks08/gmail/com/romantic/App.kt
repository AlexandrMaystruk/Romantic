package maystruks08.gmail.com.romantic

import android.app.Application
import maystruks08.gmail.com.romantic.core.di.application.AndroidModule
import maystruks08.gmail.com.romantic.core.di.application.AppComponent
import maystruks08.gmail.com.romantic.core.di.application.DaggerAppComponent
import maystruks08.gmail.com.romantic.core.di.hike.HikeComponent
import maystruks08.gmail.com.romantic.core.di.news.NewsComponent
import maystruks08.gmail.com.romantic.core.di.root.RootComponent


class App : Application() {

    companion object {

        lateinit var appComponent: AppComponent

        var rootComponent: RootComponent? = null
            get () {
                if (field == null)
                    field = appComponent.rootComponent()
                return field
            }

        var hikeComponent: HikeComponent? = null
            get () {
                if (field == null)
                    field = rootComponent?.hikeComponent()
                return field
            }

        var newsComponent: NewsComponent? = null
            get () {
                if (field == null)
                    field = rootComponent?.newsComponent()
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