package maystruks08.gmail.com.romantic.ui.selectedhike

import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView


interface SelectedHikeContract {

    interface View : IView

    interface Presenter : IPresenter<View> {

        fun onParticipantClick()

        fun onGroupChatClick()

        fun onTrainingCalendarClick()

        fun onRouteClick()

        fun onMyPostClick()

        fun onToolsClick()

        fun onMaterialsClick()

        fun onHikeInformationClick()
    }
}
