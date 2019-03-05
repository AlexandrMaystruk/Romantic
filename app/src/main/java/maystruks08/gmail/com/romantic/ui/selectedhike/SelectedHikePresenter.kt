package maystruks08.gmail.com.romantic.ui.selectedhike

import maystruks08.gmail.com.domain.interactor.hike.HikeInteractor
import maystruks08.gmail.com.romantic.core.ABasePresenter
import javax.inject.Inject

class SelectedHikePresenter @Inject constructor(val hikeInteractor: HikeInteractor) :
    SelectedHikeContract.Presenter, ABasePresenter<SelectedHikeContract.View>() {

}