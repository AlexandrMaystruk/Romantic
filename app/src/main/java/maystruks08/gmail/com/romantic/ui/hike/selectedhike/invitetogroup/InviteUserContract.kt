package maystruks08.gmail.com.romantic.ui.hike.selectedhike.invitetogroup

import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView


interface InviteUserContract {

    interface View : IView {

        fun showUsers(users: List<User>)

        fun inviteUserSuccess()
    }

    interface Presenter : IPresenter<View> {

        fun initUserList()

        fun onUserClicked(hikeId: Long, user: User)

        fun onInviteUserClicked(user: User, postPosition: Int, hikeId: Long)

        fun onRemoveUserClicked(user: User, postPosition: Int, hikeId: Long)

        fun onSaveClicked(hikeId: Long)
    }
}
