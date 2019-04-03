package maystruks08.gmail.com.romantic

import android.app.Application
import maystruks08.gmail.com.romantic.core.di.application.AndroidModule
import maystruks08.gmail.com.romantic.core.di.application.AppComponent
import maystruks08.gmail.com.romantic.core.di.application.DaggerAppComponent
import maystruks08.gmail.com.romantic.core.di.chat.ChatComponent
import maystruks08.gmail.com.romantic.core.di.event.EventComponent
import maystruks08.gmail.com.romantic.core.di.hike.HikeComponent
import maystruks08.gmail.com.romantic.core.di.launcher.LauncherComponent
import maystruks08.gmail.com.romantic.core.di.myhike.MyHikesComponent
import maystruks08.gmail.com.romantic.core.di.news.NewsComponent
import maystruks08.gmail.com.romantic.core.di.profile.ProfileComponent
import maystruks08.gmail.com.romantic.core.di.selectedhike.route.RouteComponent
import maystruks08.gmail.com.romantic.core.di.selectedhike.SelectedHikeComponent
import maystruks08.gmail.com.romantic.core.di.selectedhike.participant.ParticipantComponent


class App : Application() {

    companion object {

        lateinit var appComponent: AppComponent

        var launcherComponent: LauncherComponent? = null
            get () {
                if (field == null)
                    field = appComponent.launcherComponent()
                return field
            }

        var hikeComponent: HikeComponent? = null
            get () {
                if (field == null)
                    field = appComponent.hikeComponent()
                return field
            }

        var myHikeComponent: MyHikesComponent? = null
            get () {
                if (field == null)
                    field = appComponent.myHikesComponent()
                return field
            }

        var eventComponent: EventComponent? = null
            get () {
                if (field == null)
                    field = appComponent.eventComponent()
                return field
            }

        var selectedHikeComponent: SelectedHikeComponent? = null
            get () {
                if (field == null)
                    field = appComponent.selectedHikeComponent()
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
                    field = appComponent.newsComponent()
                return field
            }

        var chatComponent: ChatComponent? = null
            get () {
                if (field == null)
                    field = appComponent.chatComponent()
                return field
            }

        var profileComponent: ProfileComponent? = null
            get () {
                if (field == null)
                    field = appComponent.profileComponent()
                return field
            }

        var participantComponent: ParticipantComponent? = null
            get () {
                if (field == null)
                    field = appComponent.participantComponent()
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

    }

}