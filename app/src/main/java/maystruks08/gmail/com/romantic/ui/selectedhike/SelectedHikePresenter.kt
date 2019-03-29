package maystruks08.gmail.com.romantic.ui.selectedhike

import maystruks08.gmail.com.romantic.core.base.BasePresenter
import maystruks08.gmail.com.romantic.core.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SelectedHikePresenter @Inject constructor(val router: Router) :
    SelectedHikeContract.Presenter, BasePresenter<SelectedHikeContract.View>() {
    override fun onParticipantClick() {
    }

    override fun onGroupChatClick() {
    }

    override fun onTrainingCalendarClick() {
    }

    override fun onRouteClick() {
        router.navigateTo(Screens.RouteScreen())
    }

    override fun onMyPostClick() {
    }

    override fun onToolsClick() {
    }

    override fun onMaterialsClick() {
    }

    override fun onHikeInformationClick() {
    }

}