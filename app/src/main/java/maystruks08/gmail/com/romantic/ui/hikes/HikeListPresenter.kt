package maystruks08.gmail.com.romantic.ui.hikes

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

    override fun initUI(typeHike: TypeHike?) {
        view?.showLoading()
        compositeDisposable.add(
            hikeInteractor.provideHikes(typeHike)
                .subscribe(::onProvideHikesSuccess, ::onProvideHikesFailure)
        )
    }

    override fun onHikeClicked(hike: Hike) {
        router.navigateTo(Screens.SelectedHikeScreen(HikeViewModel.fromHike(hike)))
    }

    override fun onCreateHikeClicked() {
        router.navigateTo(Screens.CreateHikeScreen())
    }


    private fun onProvideHikesSuccess(hikeList: List<Hike>) {
        view?.hideLoading()
        view?.showHikes(hikeList)
    }

    private fun onProvideHikesFailure(t: Throwable) {
        view?.hideLoading()
        t.printStackTrace()
    }
}