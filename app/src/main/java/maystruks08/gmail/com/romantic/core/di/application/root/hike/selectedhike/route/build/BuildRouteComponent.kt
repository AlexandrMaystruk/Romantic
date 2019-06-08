package maystruks08.gmail.com.romantic.core.di.application.root.hike.selectedhike.route.build

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.buildRoute.BuildRouteFragment


@Subcomponent(modules = [BuildRouteModule::class])
@BuildRouteScope
interface BuildRouteComponent {

    fun inject(buildRouteFragment: BuildRouteFragment)

}