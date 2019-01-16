package maystruks08.gmail.com.romantic.ui.financerhelper

import maystruks08.gmail.com.domain.interactor.hike.HikeListInteractor
import maystruks08.gmail.com.romantic.core.ABasePresenter
import javax.inject.Inject

class FinancerHelperPresenter @Inject constructor(val hikeListListInteractor: HikeListInteractor) :
    FinancerHelperContract.Presenter, ABasePresenter<FinancerHelperContract.View>() {

    override fun initUI() {

    }
}