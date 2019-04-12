package maystruks08.gmail.com.romantic.ui.main.pager

import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.domain.interactor.hike.HikeInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import maystruks08.gmail.com.romantic.core.navigation.Screens
import maystruks08.gmail.com.romantic.ui.viewmodel.HikeViewModel
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class HikeListPresenter @Inject constructor(
    private val hikeInteractor: HikeInteractor,
    private val router: Router
) :
    HikeListContract.Presenter, BasePresenter<HikeListContract.View>() {

    override fun loadHikeData() {
        view?.showLoading()
        compositeDisposable.add(
            hikeInteractor.getHikesFromFireStore()
                .subscribe(::onDownloadHikesSuccess, ::onDownloadHikesFailure)
        )
    }

    override fun initFragment(typeHike: TypeHike?, position: Int) {
        view?.showLoading()
        compositeDisposable.add(
            hikeInteractor.provideHikes(typeHike)
                .subscribe(::onProvideHikesSuccess, ::onProvideHikesFailure)
        )
    }

    override fun onHikeClicked(hike: Hike) {
        router.navigateTo(Screens.SelectedHikeScreen(HikeViewModel.fromHike(hike)))
    }

    private fun onProvideHikesSuccess(pair: Pair<List<Hike>, Int>) {
        view?.hideLoading()
        view?.showHikes(pair.first, pair.second)
    }

    private fun onProvideHikesFailure(t: Throwable) {
        view?.hideLoading()
        t.printStackTrace()
    }

    private fun onDownloadHikesSuccess() {
        view?.hideLoading()
        initFragment(TypeHike.MOUNTAIN, 0)
    }

    private fun onDownloadHikesFailure(t: Throwable) {
        view?.hideLoading()
        t.printStackTrace()
    }
}