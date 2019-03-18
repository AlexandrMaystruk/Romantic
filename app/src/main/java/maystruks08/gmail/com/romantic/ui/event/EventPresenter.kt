package maystruks08.gmail.com.romantic.ui.event

import maystruks08.gmail.com.domain.interactor.event.EventInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import javax.inject.Inject

class EventPresenter @Inject constructor(val eventInteractor: EventInteractor) :
    EventContract.Presenter, BasePresenter<EventContract.View>() {

}