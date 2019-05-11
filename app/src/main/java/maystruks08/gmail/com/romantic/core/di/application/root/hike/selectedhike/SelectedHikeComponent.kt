package maystruks08.gmail.com.romantic.core.di.application.root.hike.selectedhike

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.core.di.application.root.hike.selectedhike.participant.ParticipantComponent
import maystruks08.gmail.com.romantic.core.di.application.root.hike.selectedhike.route.RouteComponent
import maystruks08.gmail.com.romantic.ui.hike.selectedhike.SelectedHikeFragment


@Subcomponent(modules = [SelectedHikeModule::class])
@SelectedHikeScope
interface SelectedHikeComponent {

    fun inject(hikeFragment: SelectedHikeFragment)

    fun routeComponent(): RouteComponent

    fun participantComponent(): ParticipantComponent

}