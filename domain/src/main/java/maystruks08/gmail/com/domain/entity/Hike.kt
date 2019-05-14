package maystruks08.gmail.com.domain.entity

import java.util.*

data class Hike(
    val id: Long,
    var typeHike: TypeHike,
    var dateStart: Date,
    var dateEnd: Date,
    var hikeChief: String,
    var region: String,
    var category: Category,
    val route: MutableList<Route> = mutableListOf(),
    val group: MutableList<Participant> = mutableListOf(),
    val tools: MutableList<Tool> = mutableListOf(),
    val materials: MutableList<Material> = mutableListOf(),
    val trainingCalendar: MutableList<HikeEvent> = mutableListOf()
) {

    //TODO Work with hike participants
    fun addParticipantToGroup(participant: Participant) {
        group.add(participant)
    }

    fun removeParticipantFromGroup(participant: Participant) {
        group.remove(participant)
    }

    fun updateParticipant(participant: Participant) {
        val item = group.firstOrNull { it.user.id == participant.user.id }
        if (item != null) {
            group.remove(item)
        }
        group.add(participant)
    }

    //TODO Work with hike tools
    fun addTool(tool: Tool) {
        tools.add(tool)
    }

    fun removeTool(tool: Tool) {
        tools.remove(tool)
    }

    fun updateTool(tool: Tool) {
        val item = tools.firstOrNull { it.name == tool.name }
        if (item != null) {
            tools.remove(item)
        }
        tools.add(tool)
    }

    //TODO Work with hike materials
    fun addMaterials(material: Material) {
        materials.add(material)
    }

    fun removeMaterials(material: Material) {
        materials.remove(material)
    }

    fun updateMaterials(material: Material) {
        val item = materials.firstOrNull { it.name == material.name }
        if (item != null) {
            materials.remove(item)
        }
        materials.add(material)
    }

    //TODO Work with hike route
    fun addRoute(hikeRoute: Route) {
       val mainRoute =  route.firstOrNull { it.type == RouteType.MAIN }
        if(mainRoute == null){
            route.add(hikeRoute)
        }
    }

    fun removeRoute(hikeRoute: Route) {
        route.remove(hikeRoute)
    }

    fun addGeoPointInRoute(routeId: Long, point: GeoPoint ) {
        route.firstOrNull { it.id == routeId }?.let {
            it.geoPoints.add(point)
        }
    }

    fun removeGeoPointInRoute(routeId: Long, point: GeoPoint ) {
        route.firstOrNull { it.id == routeId }?.let {
            it.geoPoints.remove(point)
        }
    }
}