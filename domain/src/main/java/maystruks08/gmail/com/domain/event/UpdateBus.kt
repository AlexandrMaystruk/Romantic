package maystruks08.gmail.com.domain.event

import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateBus @Inject constructor(): DomainBus() {


    private val updateParticipantEvent =  createEventPublisher<UpdateParticipantEvent>()

    fun postUpdateParticipantEvent(participants: List<Participant>) = updateParticipantEvent.onNext(UpdateParticipantEvent(participants))

    fun updateParticipantEvent() = updateParticipantEvent


    private val updateHikesEvent = createEventPublisher<UpdateHikesEvent>()

    fun updateLocalHikesEvent() = updateHikesEvent

    fun postUpdateLocalHikes(hikes: List<Hike>) = updateHikesEvent.onNext(UpdateHikesEvent(hikes))


    private val updateUserEvent = createEventPublisher<UpdateUsersEvent>()

    fun updateLocalUserEvent() = updateUserEvent

    fun postUpdateLocalUsers(users: List<User>) = updateUserEvent.onNext(UpdateUsersEvent(users))

}