package maystruks08.gmail.com.romantic.core.di.application.root.hike.selectedhike.route

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.ui.selectedhike.route.RouteFragment


@Subcomponent(modules = [RouteModule::class])
@RouteScope
interface RouteComponent {

    fun inject(routeFragment: RouteFragment)

}