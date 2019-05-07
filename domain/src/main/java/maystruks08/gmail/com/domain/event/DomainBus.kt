package maystruks08.gmail.com.domain.event

import io.reactivex.subjects.PublishSubject

open class DomainBus {

    fun <T: Event> createEventPublisher (): PublishSubject<T>
            = PublishSubject.create<T>()

}