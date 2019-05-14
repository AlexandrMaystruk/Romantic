package maystruks08.gmail.com.romantic.core.di.application.root.hike.selectedhike.route

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.RouteFragment
import maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.routelist.RouteListFragment


@Subcomponent(modules = [RouteModule::class])
@RouteScope
interface RouteComponent {

    fun inject(routeFragment: RouteFragment)

    fun inject(routeListFragment: RouteListFragment)


}