package maystruks08.gmail.com.romantic.ui.hike

import maystruks08.gmail.com.domain.interactor.hike.HikeInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import javax.inject.Inject

class HikePresenter @Inject constructor(val hikeInteractor: HikeInteractor) :
    HikeContract.Presenter, BasePresenter<HikeContract.View>() {


}