package maystruks08.gmail.com.romantic.ui.selectedhike.participant

import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView


interface ParticipantContract {

    interface View : IView {

        fun showParticipant(participants: List<Participant>)

    }

    interface Presenter : IPresenter<View> {

        fun initParticipantList(hikeId: Long)

        fun onAddParticipantClicked(hikeId: Long)

        fun onParticipantClicked(participant: Participant)

        fun onParticipantRemoveClicked(position: Int, participant: Participant)

    }
}
