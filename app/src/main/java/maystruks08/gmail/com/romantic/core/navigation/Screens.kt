package maystruks08.gmail.com.romantic.core.navigation

import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.romantic.ui.chat.ChatFragment
import maystruks08.gmail.com.romantic.ui.createhike.CreateNewHikeFragment
import maystruks08.gmail.com.romantic.ui.event.EventFragment
import maystruks08.gmail.com.romantic.ui.myhikes.MyHikesFragment
import maystruks08.gmail.com.romantic.ui.launcher.SingInFragment
import maystruks08.gmail.com.romantic.ui.launcher.SingUpFragment
import maystruks08.gmail.com.romantic.ui.main.pager.RootTabFragment
import maystruks08.gmail.com.romantic.ui.news.NewsFragment
import maystruks08.gmail.com.romantic.ui.profile.ProfileFragment
import maystruks08.gmail.com.romantic.ui.profilesettings.ProfileSettingsFragment
import maystruks08.gmail.com.romantic.ui.selectedhike.SelectedHikeFragment
import maystruks08.gmail.com.romantic.ui.selectedhike.participant.ParticipantFragment
import maystruks08.gmail.com.romantic.ui.selectedhike.route.RouteFragment
import maystruks08.gmail.com.romantic.ui.viewmodel.HikeViewModel
import maystruks08.gmail.com.romantic.ui.viewmodel.ParticipantViewModel
import maystruks08.gmail.com.romantic.ui.viewmodel.UserViewModel

object Screens {

    class SignInScreen : AppScreen() {
        override fun getFragment() = SingInFragment.getInstance()
    }

    class SignUpScreen : AppScreen() {
        override fun getFragment() = SingUpFragment.getInstance()
    }

    class RootTabScreen : AppScreen() {
        override fun getFragment() = RootTabFragment.getInstance()
    }

    class NewsScreen : AppScreen() {
        override fun getFragment() = NewsFragment.getInstance()
    }

    class MessageScreen : AppScreen() {
        override fun getFragment() = ChatFragment.getInstance()
    }

    class EventScreen : AppScreen() {
        override fun getFragment() = EventFragment.getInstance()
    }

    class MyHikesScreen : AppScreen() {
        override fun getFragment() = MyHikesFragment.getInstance()
    }

    class SelectedHikeScreen(private val hike: HikeViewModel) : AppScreen() {
        override fun getFragment() = SelectedHikeFragment.getInstance(hike)
    }

    class CreateHikeScreen : AppScreen() {
        override fun getFragment() = CreateNewHikeFragment.getInstance()
    }

    class RouteScreen : AppScreen() {
        override fun getFragment() = RouteFragment.getInstance()
    }

    class ProfileScreen(private val participant: User) : AppScreen() {
        override fun getFragment(): ProfileFragment{
            return if(participant is Participant){
                ProfileFragment.getInstance(ParticipantViewModel.fromParticipant(participant))
            } else {
                ProfileFragment.getInstance(UserViewModel.fromUser(participant))
            }
        }
    }

    class ProfileSettingsScreen(private val user: User) : AppScreen() {
        override fun getFragment() = ProfileSettingsFragment.getInstance(UserViewModel.fromUser(user))
    }

    class ParticipantScreen(private val hikeId: String) : AppScreen() {
        override fun getFragment() = ParticipantFragment.getInstance(hikeId)
    }


}