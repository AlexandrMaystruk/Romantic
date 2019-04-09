package maystruks08.gmail.com.romantic.ui.selectedhike.participant

import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView


interface ParticipantContract {

    interface View : IView{

        fun showParticipant(participants: List<User>)

    }

    interface Presenter : IPresenter<View> {

       fun initUserList()

        fun onUserClicked(user: User)

        fun onInviteUserClicked(user: User)

    }
}
