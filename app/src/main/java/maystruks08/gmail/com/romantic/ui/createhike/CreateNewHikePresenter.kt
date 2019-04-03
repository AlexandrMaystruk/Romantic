package maystruks08.gmail.com.romantic.ui.createhike

import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.interactor.hike.HikeInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class CreateNewHikePresenter @Inject constructor(
    private val hikeInteractor: HikeInteractor,
    private val router: Router
) :
    CreateNewHikeContract.Presenter, BasePresenter<CreateNewHikeContract.View>() {

    override fun createHike(hike: Hike) {
        compositeDisposable.add(
            hikeInteractor.createNewHike(hike)
                .subscribe(::onCreateHikeSuccess, ::onCreateHikeFailure)
        )
    }

    private fun onCreateHikeSuccess() {
        view?.hideLoading()
        router.exit()
    }

    private fun onCreateHikeFailure(t: Throwable) {
        view?.hideLoading()
        view?.showError(t)
    }
}