package maystruks08.gmail.com.romantic.core.di.event

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.ui.event.EventFragment

@Subcomponent(modules = [EventModule::class])
@EventScope
interface EventComponent {

    fun inject(eventFragment: EventFragment)
}