package maystruks08.gmail.com.romantic.ui.createhike

import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.interactor.hike.HikeInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import javax.inject.Inject

class CreateNewHikePresenter @Inject constructor(val hikeInteractor: HikeInteractor) :
    CreateNewHikeContract.Presenter, BasePresenter<CreateNewHikeContract.View>() {

    override fun createHike(hike: Hike) {
    }

}