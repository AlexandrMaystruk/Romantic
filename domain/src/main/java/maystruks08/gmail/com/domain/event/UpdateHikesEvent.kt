package maystruks08.gmail.com.domain.event

import maystruks08.gmail.com.domain.entity.Hike

data class UpdateHikesEvent(val hikes: List<Hike>) : Event()