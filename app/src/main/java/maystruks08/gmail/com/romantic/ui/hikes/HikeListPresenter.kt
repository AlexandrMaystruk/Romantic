package maystruks08.gmail.com.romantic.ui.hikes

import maystruks08.gmail.com.domain.interactor.hike.HikeListInteractor
import maystruks08.gmail.com.romantic.core.ABasePresenter
import javax.inject.Inject

class HikeListPresenter @Inject constructor(val hikeListListInteractor: HikeListInteractor) :
    HikeListContract.Presenter, ABasePresenter<HikeListContract.View>() {


    override fun initUI() {

    }
}