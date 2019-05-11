package maystruks08.gmail.com.romantic.ui.hike.myhikes

import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.interactor.hike.HikeInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import maystruks08.gmail.com.romantic.core.navigation.Screens
import maystruks08.gmail.com.romantic.ui.viewmodel.HikeViewModel
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MyHikePresenter @Inject constructor(
    private val hikeInteractor: HikeInteractor,
    private val router: Router
) :
    MyHikesContract.Presenter, BasePresenter<MyHikesContract.View>() {


    override fun onHikeClicked(hike: Hike) {
        router.navigateTo(Screens.SelectedHikeScreen(HikeViewModel.fromHike(hike)))
    }

    override fun onCreateHikeClicked() {
        router.navigateTo(Screens.CreateHikeScreen())
    }

    override fun initFragmentByUser() {
        view?.showLoading()
        compositeDisposable.add(
            hikeInteractor.provideUserHikes()
                .subscribe(::onProvideHikesSuccess, ::onProvideHikesFailure)
        )
    }

    private fun onProvideHikesSuccess(hikes: List<Hike>) {
        view?.hideLoading()
        view?.showHikes(hikes)
    }

    private fun onProvideHikesFailure(t: Throwable) {
        view?.hideLoading()
        t.printStackTrace()
    }

    override fun onLeaveFromHikeClicked(position: Int, hike: Hike) {
        view?.showHikeRemoved(position)
        compositeDisposable.add(
            hikeInteractor.leaveFromHikeGroup(hike)
                .subscribe(::onLeaveFromHikeGroupSuccess, ::onLeaveFromHikeGroupFailure)
        )
    }

    private fun onLeaveFromHikeGroupSuccess() {
        view?.hideLoading()
    }

    private fun onLeaveFromHikeGroupFailure(t: Throwable) {
        view?.hideLoading()
        t.printStackTrace()
    }

    override fun onDeleteHikeClicked(position: Int, hike: Hike) {
        view?.showHikeRemoved(position)
        compositeDisposable.add(
            hikeInteractor.deleteHike(hike)
                .subscribe(::onDeleteHikeSuccess, ::onDeleteHikeFailure)
        )
    }

    private fun onDeleteHikeSuccess() {
        view?.hideLoading()
    }

    private fun onDeleteHikeFailure(t: Throwable) {
        view?.hideLoading()
        t.printStackTrace()
    }
}