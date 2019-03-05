package maystruks08.gmail.com.romantic.ui.selectedhike.financerhelper

import maystruks08.gmail.com.domain.interactor.hike.HikeInteractor
import maystruks08.gmail.com.romantic.core.ABasePresenter
import javax.inject.Inject

class FinancierHelperPresenter @Inject constructor(val hikeInteractor: HikeInteractor) :
    FinancerHelperContract.Presenter, ABasePresenter<FinancerHelperContract.View>() {

    override fun initUI() {

    }
}