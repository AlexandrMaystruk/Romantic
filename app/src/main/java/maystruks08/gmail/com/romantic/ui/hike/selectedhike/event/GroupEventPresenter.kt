package maystruks08.gmail.com.romantic.ui.hike.selectedhike.event

import maystruks08.gmail.com.domain.interactor.event.EventInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import javax.inject.Inject

class GroupEventPresenter @Inject constructor(val eventInteractor: EventInteractor) :
    GroupeEventContract.Presenter, BasePresenter<GroupeEventContract.View>() {

}