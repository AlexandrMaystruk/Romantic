package maystruks08.gmail.com.romantic.core.di.selectedhike

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.core.di.selectedhike.route.RouteComponent
import maystruks08.gmail.com.romantic.ui.selectedhike.SelectedHikeFragment


@Subcomponent(modules = [SelectedHikeModule::class])
@SelectedHikeScope
interface SelectedHikeComponent {

    fun inject(hikeFragment: SelectedHikeFragment)

    fun routeComponent(): RouteComponent
}