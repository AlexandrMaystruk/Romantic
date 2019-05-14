package maystruks08.gmail.com.romantic.ui.hike.selectedhike

import maystruks08.gmail.com.romantic.core.base.BasePresenter
import maystruks08.gmail.com.romantic.core.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SelectedHikePresenter @Inject constructor(val router: Router) :
    SelectedHikeContract.Presenter, BasePresenter<SelectedHikeContract.View>() {

    override fun onParticipantClick(hikeId: Long) {
        router.navigateTo(Screens.ParticipantScreen(hikeId))
    }

    override fun onGroupChatClick() {
    }

    override fun onTrainingCalendarClick() {
    }

    override fun onRouteClick(hikeId: Long) {
        router.navigateTo(Screens.RouteListScreen(hikeId))
    }

    override fun onMyPostClick() {
    }

    override fun onToolsClick() {
    }

    override fun onMaterialsClick() {
    }

    override fun onHikeInformationClick() {
    }

    override fun onChangeHikeClicked(hikeId: Long) {

    }

    override fun onRemoveHikeClicked(hikeId: Long) {
    }

}