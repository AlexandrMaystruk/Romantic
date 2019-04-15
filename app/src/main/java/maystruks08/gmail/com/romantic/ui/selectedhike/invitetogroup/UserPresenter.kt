package maystruks08.gmail.com.romantic.ui.selectedhike.invitetogroup

import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.entity.UserPost
import maystruks08.gmail.com.domain.interactor.participant.ParticipantInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import maystruks08.gmail.com.romantic.core.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class UserPresenter @Inject constructor(
    private val router: Router,
    private val interactor: ParticipantInteractor
) : InviteUserContract.Presenter, BasePresenter<InviteUserContract.View>() {

    override fun initUserList() {
        compositeDisposable.add(
            interactor.getUsers()
                .subscribe(::onGetUserSuccess,::onGetUserFailure )
        )
    }

    private fun onGetUserSuccess(users: List<User>) {
        view?.showUsers(users)
    }

    private fun onGetUserFailure(t: Throwable){
        t.printStackTrace()
    }



    override fun onUserClicked(user: User) {
        router.navigateTo(Screens.ProfileScreen(Participant(UserPost.BOSS, 0L, user)))
    }

    override fun onInviteUserClicked(user: User) {

    }

    override fun onSaveClicked() {
        //todo save change
        router.exit()
    }

}
