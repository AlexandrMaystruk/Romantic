package maystruks08.gmail.com.romantic

import android.app.Application
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import maystruks08.gmail.com.romantic.core.di.application.AndroidModule
import maystruks08.gmail.com.romantic.core.di.application.AppComponent
import maystruks08.gmail.com.romantic.core.di.application.DaggerAppComponent
import maystruks08.gmail.com.romantic.core.di.application.root.chat.ChatComponent
import maystruks08.gmail.com.romantic.core.di.application.root.event.EventComponent
import maystruks08.gmail.com.romantic.core.di.application.root.hike.HikeComponent
import maystruks08.gmail.com.romantic.core.di.application.launcher.LauncherComponent
import maystruks08.gmail.com.romantic.core.di.application.root.RootComponent
import maystruks08.gmail.com.romantic.core.di.application.root.hike.createhike.CreateHikeComponent
import maystruks08.gmail.com.romantic.core.di.application.root.hike.myhike.MyHikesComponent
import maystruks08.gmail.com.romantic.core.di.application.root.news.NewsComponent
import maystruks08.gmail.com.romantic.core.di.application.root.profile.ProfileComponent
import maystruks08.gmail.com.romantic.core.di.application.root.hike.selectedhike.route.RouteComponent
import maystruks08.gmail.com.romantic.core.di.application.root.hike.selectedhike.SelectedHikeComponent
import maystruks08.gmail.com.romantic.core.di.application.root.hike.selectedhike.participant.ParticipantComponent


class App : Application() {

    companion object {

        lateinit var appComponent: AppComponent

        var launcherComponent: LauncherComponent? = null
            get () {
                if (field == null)
                    field = appComponent.launcherComponent()
                return field
            }

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


        var createHikeComponent: CreateHikeComponent? = null
            get () {
                if (field == null)
                    field = hikeComponent?.createComponent()
                return field
            }


        var myHikeComponent: MyHikesComponent? = null
            get () {
                if (field == null)
                    field = hikeComponent?.myHikesComponent()
                return field
            }

        var eventComponent: EventComponent? = null
            get () {
                if (field == null)
                    field = rootComponent?.eventComponent()
                return field
            }

        var selectedHikeComponent: SelectedHikeComponent? = null
            get () {
                if (field == null)
                    field = hikeComponent?.selectedHikeComponent()
                return field
            }

        var routeComponent: RouteComponent? = null
            get () {
                if (field == null)
                    field = selectedHikeComponent?.routeComponent()
                return field
            }


        var newsComponent: NewsComponent? = null
            get () {
                if (field == null)
                    field = rootComponent?.newsComponent()
                return field
            }

        var chatComponent: ChatComponent? = null
            get () {
                if (field == null)
                    field = rootComponent?.chatComponent()
                return field
            }

        var profileComponent: ProfileComponent? = null
            get () {
                if (field == null)
                    field = rootComponent?.profileComponent()
                return field
            }

        var participantComponent: ParticipantComponent? = null
            get () {
                if (field == null)
                    field = selectedHikeComponent?.participantComponent()
                return field
            }


        fun clearLauncherComponent() {
            launcherComponent = null
        }

        fun clearMeHikesComponent() {
            myHikeComponent = null
        }

        fun clearHikeListComponent() {
            hikeComponent = null
        }

        fun clearNewsComponent() {
            newsComponent = null
        }

        fun clearSelectedHikeComponent() {
            selectedHikeComponent = null
        }

        fun clearChatComponent() {
            chatComponent = null
        }

    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .androidModule(AndroidModule(this))
            .build()

        RxJavaPlugins.setErrorHandler { e ->
            if (e is UndeliverableException) {
                System.err.println(e.message)
            } else {
                Thread.currentThread().also { thread ->
                    thread.uncaughtExceptionHandler.uncaughtException(thread, e)
                }
            }
        }


    }

}