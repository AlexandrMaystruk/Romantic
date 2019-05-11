package maystruks08.gmail.com.romantic.ui.hike.selectedhike.participant

import android.util.Log
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.exceptions.ParticipantDataSync
import maystruks08.gmail.com.domain.interactor.hike.selectedhike.participant.ParticipantInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import maystruks08.gmail.com.romantic.core.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class ParticipantPresenter @Inject constructor(
    private val router: Router,
    private val interactor: ParticipantInteractor
) : ParticipantContract.Presenter, BasePresenter<ParticipantContract.View>() {


    override fun initParticipantList(hikeId: Long) {
        view?.showLoading()
        compositeDisposable.add(
            interactor.getHikeParticipants(hikeId)
                .subscribe(::onGetParticipantSuccess, ::onGetParticipantFailure)
        )
    }

    private fun onGetParticipantSuccess(participants: List<Participant>) {
        view?.hideLoading()
        view?.showParticipant(participants)
        compositeDisposable.add(
            interactor.updateCashedGroup(participants)
                .subscribe(::onUpdateCashParticipantSuccess, ::onGetParticipantFailure)
        )
    }

    private fun onUpdateCashParticipantSuccess(participants: List<Participant>) {
        view?.showParticipant(participants)
    }

    private fun onGetParticipantFailure(t: Throwable) {
        if (t is ParticipantDataSync) {
            Log.d(TAG, "All data is synced")
        } else {
            t.printStackTrace()
        }
    }

    override fun onAddParticipantClicked(hikeId: Long) {
        router.navigateTo(Screens.InviteeParticipantScreen(hikeId))
    }

    override fun onParticipantClicked(hikeId: Long, participant: Participant) {
        router.navigateTo(Screens.ProfileScreen(participant))
    }

    override fun onParticipantRemoveClicked(position: Int, participant: Participant, hikeId: Long) {
        view?.removeParticipant(position)
        compositeDisposable.add(
            interactor.removeParticipant(hikeId, participant).subscribe(
                ::onRemoveSuccess,
                ::onRemoveFailure
            )
        )
    }

    private fun onRemoveSuccess() {
        Log.d(TAG, "Remove success")
    }

    private fun onRemoveFailure(t: Throwable) {
        t.printStackTrace()
    }

    companion object {

        const val TAG = "Participant"
    }

}
