package maystruks08.gmail.com.romantic

import android.app.Application
import maystruks08.gmail.com.romantic.core.di.application.AndroidModule
import maystruks08.gmail.com.romantic.core.di.application.AppComponent
import maystruks08.gmail.com.romantic.core.di.application.DaggerAppComponent
import maystruks08.gmail.com.romantic.core.di.authentication.AuthenticationComponent
import maystruks08.gmail.com.romantic.core.di.hike.HikeComponent
import maystruks08.gmail.com.romantic.core.di.news.NewsComponent


class App : Application() {

    companion object {

        lateinit var appComponent: AppComponent

        var authenticationComponent: AuthenticationComponent? = null
            get () {
                if (field == null)
                    field = appComponent.authenticationComponent()
                return field
            }

        var hikeComponent: HikeComponent? = null
            get () {
                if (field == null)
                    field = appComponent.hikeComponent()
                return field
            }

        var newsComponent: NewsComponent? = null
            get () {
                if (field == null)
                    field = appComponent.newsComponent()
                return field
            }
    }

    fun clearHikeListComponent() {
        hikeComponent = null
    }

    fun clearNewsComponent() {
        newsComponent = null
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .androidModule(AndroidModule(this))
            .build()

    }

}