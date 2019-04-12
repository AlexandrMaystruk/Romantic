package maystruks08.gmail.com.romantic.core.di.application.root

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.core.di.application.root.chat.ChatComponent
import maystruks08.gmail.com.romantic.core.di.application.root.event.EventComponent
import maystruks08.gmail.com.romantic.core.di.application.root.hike.HikeComponent

import maystruks08.gmail.com.romantic.core.di.application.root.hike.myhike.MyHikesComponent
import maystruks08.gmail.com.romantic.core.di.application.root.news.NewsComponent
import maystruks08.gmail.com.romantic.core.di.application.root.profile.ProfileComponent
import maystruks08.gmail.com.romantic.core.di.application.root.hike.selectedhike.SelectedHikeComponent
import maystruks08.gmail.com.romantic.core.di.application.root.hike.selectedhike.participant.ParticipantComponent
import maystruks08.gmail.com.romantic.ui.main.RootActivity

@Subcomponent(modules = [RootActivityModule::class])
@RootScope
interface RootComponent {

    fun inject(rootActivity: RootActivity)

    fun hikeComponent(): HikeComponent

    fun newsComponent(): NewsComponent

    fun chatComponent(): ChatComponent

    fun eventComponent(): EventComponent

    fun profileComponent(): ProfileComponent

}