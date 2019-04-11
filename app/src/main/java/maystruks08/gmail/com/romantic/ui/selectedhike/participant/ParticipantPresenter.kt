package maystruks08.gmail.com.romantic.ui.selectedhike.participant

import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.interactor.participant.ParticipantInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import maystruks08.gmail.com.romantic.core.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class ParticipantPresenter @Inject constructor(
    private val router: Router,
    private val interactor: ParticipantInteractor
) : ParticipantContract.Presenter, BasePresenter<ParticipantContract.View>() {


    override fun initUserList(hikeId: String) {
        compositeDisposable.add(
            interactor.getHikeParticipants(hikeId)
                .subscribe(::onGetParticipantSuccess,::onGetParticipantFailure )
        )
    }

    private fun onGetParticipantSuccess(participants: List<Participant>) {
        view?.showParticipant(participants)
    }

    private fun onGetParticipantFailure(t: Throwable){
        t.printStackTrace()
    }



    override fun onUserClicked(participant: Participant) {
        router.navigateTo(Screens.ProfileScreen(participant))
    }

    override fun onInviteUserClicked(participant: Participant) {

    }


}
