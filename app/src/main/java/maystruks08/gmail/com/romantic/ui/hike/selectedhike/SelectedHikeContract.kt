package maystruks08.gmail.com.romantic.ui.hike.selectedhike

import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView


interface SelectedHikeContract {

    interface View : IView

    interface Presenter : IPresenter<View> {

        fun onParticipantClick(hikeId: Long)

        fun onGroupChatClick()

        fun onTrainingCalendarClick()

        fun onRouteClick()

        fun onMyPostClick()

        fun onToolsClick()

        fun onMaterialsClick()

        fun onHikeInformationClick()

        fun onChangeHikeClicked(hikeId: Long)

        fun onRemoveHikeClicked(hikeId: Long)
    }
}
