package maystruks08.gmail.com.romantic.core.di.application.root.event

import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.data.repository.EventRepositoryImpl
import maystruks08.gmail.com.domain.interactor.event.EventInteractor
import maystruks08.gmail.com.domain.interactor.event.EventInteractorImpl
import maystruks08.gmail.com.domain.repository.EventRepository
import maystruks08.gmail.com.romantic.ui.event.EventContract
import maystruks08.gmail.com.romantic.ui.event.EventPresenter

@Module
class EventModule {

    @Provides
    @EventScope
    fun presenter(eventPresenter: EventPresenter): EventContract.Presenter = eventPresenter

    @Provides
    @EventScope
    fun repository(eventRepository: EventRepositoryImpl): EventRepository = eventRepository

    @Provides
    @EventScope
    fun interactor(eventInteractorImpl: EventInteractorImpl): EventInteractor = eventInteractorImpl
}
