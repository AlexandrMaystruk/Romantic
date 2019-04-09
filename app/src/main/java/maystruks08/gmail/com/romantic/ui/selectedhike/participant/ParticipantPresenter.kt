package maystruks08.gmail.com.romantic.ui.selectedhike.participant

import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import maystruks08.gmail.com.romantic.core.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class ParticipantPresenter @Inject constructor(private val router: Router)
    : ParticipantContract.Presenter, BasePresenter<ParticipantContract.View>() {


    override fun initUserList() {

    }


   override fun onUserClicked(user: User){
       router.navigateTo(Screens.ProfileScreen(user))
   }

    override fun onInviteUserClicked(user: User) {

    }


}
