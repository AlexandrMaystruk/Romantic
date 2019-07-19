package maystruks08.gmail.com.romantic.ui.main

import maystruks08.gmail.com.domain.interactor.main.RootInteractor
import maystruks08.gmail.com.romantic.core.base.BaseActivityPresenter
import javax.inject.Inject


class RootPresenter @Inject constructor(private val rootInteractor: RootInteractor) :
    RootContract.Presenter, BaseActivityPresenter<RootContract.View>() {

    override fun logout() {
        view?.showLoading()
        compositeDisposable.add(
            rootInteractor.logout()
                .subscribe(::onLogoutSuccess, ::onLogoutFailure)
        )
    }

    override fun syncData() {
        onUploadHikes()
        onUpdateHikes()
        onUploadParticipants()
        onUpdateParticipants()
    }

    override fun onUploadHikes() {
        compositeDisposable.add(
            rootInteractor.uploadHikes().subscribe(::onUploadHikesSuccess, ::onUploadHikesFailure)
        )
    }

    override fun onUpdateHikes() {
        compositeDisposable.add(
            rootInteractor.updateHikes().subscribe(::onUpdateHikesSuccess, ::onUpdateHikesFailure)
        )
    }

    override fun onUpdateParticipants() {
        compositeDisposable.add(
            rootInteractor.updateParticipants().subscribe(
                ::onUpdateParticipantsSuccess,
                ::onUpdateParticipantsFailure
            )
        )
    }

    override fun onUploadParticipants() {
        compositeDisposable.add(
            rootInteractor.uploadParticipants().subscribe(
                ::onUploadParticipantsSuccess,
                ::onUploadParticipantsFailure
            )
        )
    }

    override fun uploadRoutes() {
        //todo uploadRoutes
    }

    override fun updateRoutes() {
        //todo updateRoutes
    }

    private fun onUploadHikesSuccess(countError: Int) {
        println("Upload hike error count: $countError")
        view?.hideLoading()
    }

    private fun onUploadHikesFailure(t: Throwable) {
        t.printStackTrace()
        view?.hideLoading()
    }

    private fun onUpdateHikesSuccess(countError: Int) {
        println("Update hike error count: $countError")
        view?.hideLoading()
    }

    private fun onUpdateHikesFailure(t: Throwable) {
        t.printStackTrace()
        view?.hideLoading()
    }


    private fun onUpdateParticipantsSuccess(countError: Int) {
        println("Update participant error count: $countError")
        view?.hideLoading()
    }

    private fun onUpdateParticipantsFailure(t: Throwable) {
        t.printStackTrace()
        view?.hideLoading()
    }


    private fun onUploadParticipantsSuccess(countError: Int) {
        println("Upload participant error count: $countError")
        view?.hideLoading()
    }

    private fun onUploadParticipantsFailure(t: Throwable) {
        t.printStackTrace()
        view?.hideLoading()
    }


    private fun onLogoutSuccess() {
        view?.hideLoading()
        view?.startLauncherActivity()
    }

    private fun onLogoutFailure(t: Throwable) {
        t.printStackTrace()
        view?.hideLoading()

    }
}
