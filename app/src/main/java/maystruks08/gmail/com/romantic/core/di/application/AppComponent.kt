package maystruks08.gmail.com.romantic.core.di.application

import dagger.Component
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
import maystruks08.gmail.com.romantic.ui.main.RootActivity
import javax.inject.Singleton

@Component (modules = [AndroidModule::class, DataAccessModule::class, NavigationModule::class, LogOutModule::class])
@Singleton
interface AppComponent {

    fun inject(rootActivity: RootActivity)

    fun launcherComponent(): LauncherComponent

    fun hikeComponent(): HikeComponent

    fun myHikesComponent(): MyHikesComponent

    fun newsComponent(): NewsComponent

    fun selectedHikeComponent(): SelectedHikeComponent

    fun chatComponent(): ChatComponent

    fun eventComponent(): EventComponent

    fun profileComponent(): ProfileComponent

    fun participantComponent(): ParticipantComponent


}