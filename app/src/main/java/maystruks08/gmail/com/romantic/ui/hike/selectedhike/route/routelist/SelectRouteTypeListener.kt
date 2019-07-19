package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.routelist

import maystruks08.gmail.com.domain.entity.RouteType

interface SelectRouteTypeListener {

    fun onTypeSelected(hikeId: Long, name: String, routeType: RouteType)
}
