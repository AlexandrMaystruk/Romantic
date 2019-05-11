package maystruks08.gmail.com.romantic.ui.hike.selectedhike.financerhelper

import maystruks08.gmail.com.domain.interactor.hike.HikeInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import javax.inject.Inject

class FinancierHelperPresenter @Inject constructor(val hikeInteractor: HikeInteractor) :
    FinancerHelperContract.Presenter, BasePresenter<FinancerHelperContract.View>() {

    override fun initUI() {

    }
}