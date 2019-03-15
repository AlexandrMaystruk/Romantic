package maystruks08.gmail.com.domain.interactor.event

import maystruks08.gmail.com.domain.repository.EventRepository
import javax.inject.Inject

class EventInteractorImpl @Inject constructor(val repository: EventRepository) : EventInteractor {
}