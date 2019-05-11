package maystruks08.gmail.com.romantic.ui.hike.selectedhike.participant

import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView


interface ParticipantContract {

    interface View : IView {

        fun showParticipant(participants: List<Participant>)

        fun removeParticipant(position: Int)
    }

    interface Presenter : IPresenter<View> {

        fun initParticipantList(hikeId: Long)

        fun onAddParticipantClicked(hikeId: Long)

        fun onParticipantClicked(hikeId: Long, participant: Participant)

        fun onParticipantRemoveClicked(position: Int, participant: Participant, hikeId: Long)

    }
}
