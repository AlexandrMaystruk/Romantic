package maystruks08.gmail.com.romantic.core.di.application.root.event

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.ui.event.EventFragment
import maystruks08.gmail.com.romantic.ui.hike.selectedhike.event.GroupEventFragment

@Subcomponent(modules = [EventModule::class])
@EventScope
interface EventComponent {

    fun inject(eventFragment: EventFragment)

    fun inject(groupEventFragment: GroupEventFragment)
}