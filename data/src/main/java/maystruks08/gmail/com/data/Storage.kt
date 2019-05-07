package maystruks08.gmail.com.data

import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Storage @Inject constructor() {

    val selectedHike: Hike? = null

    val currentUser: User? = null

    val selectedParticipant: Participant? = null

}