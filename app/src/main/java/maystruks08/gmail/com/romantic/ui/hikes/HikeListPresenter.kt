package maystruks08.gmail.com.romantic.ui.hikes

import maystruks08.gmail.com.domain.interactor.hike.HikeInteractor
import maystruks08.gmail.com.romantic.core.ABasePresenter
import javax.inject.Inject

class HikeListPresenter @Inject constructor(val hikeInteractor: HikeInteractor) :
    HikeListContract.Presenter, ABasePresenter<HikeListContract.View>() {

    override fun initUI() {

    }
}