package maystruks08.gmail.com.romantic.ui.hike.selectedhike.invitetogroup

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.entity.UserPost
import maystruks08.gmail.com.domain.event.UpdateBus
import maystruks08.gmail.com.domain.event.UpdateUsersEvent
import maystruks08.gmail.com.domain.interactor.hike.selectedhike.participant.ParticipantInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import maystruks08.gmail.com.romantic.core.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class UserPresenter @Inject constructor(
    private val router: Router,
    private val interactor: ParticipantInteractor
) : InviteUserContract.Presenter, BasePresenter<InviteUserContract.View>() {

    private val invitedUserList = mutableListOf<Participant>()

    override fun initUserList() {
        view?.showLoading()
        compositeDisposable.add(
            interactor.getUsers()
                .subscribe(::onGetUserSuccess, ::onGetUserFailure)
        )
    }

    private fun onGetUserSuccess(users: List<User>) {
        view?.hideLoading()
        view?.showUsers(users)
        compositeDisposable.add(
            interactor.updateUserCash(users)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::onGetUserUpdateSuccess,::onGetUserFailure )
        )
    }

    private fun onGetUserUpdateSuccess(users: List<User>) {
        view?.hideLoading()
        view?.showUsers(users)
    }

    private fun onGetUserFailure(t: Throwable) {
        view?.hideLoading()
        t.printStackTrace()
    }

    override fun onUserClicked(hikeId: Long, user: User) {
        router.navigateTo(Screens.ProfileScreen(Participant(UserPost.BOSS, hikeId, user)))
    }

    override fun onInviteUserClicked(user: User, postPosition: Int, hikeId: Long) {
        invitedUserList.add(Participant(UserPost.fromIndex(postPosition), hikeId, user))
    }

    override fun onRemoveUserClicked(user: User, postPosition: Int, hikeId: Long) {
        invitedUserList.remove(Participant(UserPost.fromIndex(postPosition), hikeId, user))
    }

    override fun onSaveClicked(hikeId: Long) {
        view?.showLoading()
        compositeDisposable.add(
            interactor.addParticipantsToHikeGroup(hikeId, invitedUserList)
                .subscribe(::onInviteUserSuccess, ::onInviteUserFailure)
        )
    }

    private fun onInviteUserSuccess() {
        view?.hideLoading()
        view?.inviteUserSuccess()
        router.exit()
    }

    private fun onInviteUserFailure(t: Throwable) {
        view?.hideLoading()
        t.printStackTrace()
    }

}
