package maystruks08.gmail.com.romantic.ui.hikes

import maystruks08.gmail.com.domain.interactor.hike.HikeInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import javax.inject.Inject

class HikeListPresenter @Inject constructor(val hikeInteractor: HikeInteractor) :
    HikeListContract.Presenter, BasePresenter<HikeListContract.View>() {

    override fun initUI() {

    }
}