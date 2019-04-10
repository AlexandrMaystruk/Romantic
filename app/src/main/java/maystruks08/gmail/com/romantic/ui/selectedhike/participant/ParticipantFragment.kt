package maystruks08.gmail.com.romantic.ui.selectedhike.participant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_participant.*
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.ConfigToolbar
import maystruks08.gmail.com.romantic.ui.ToolBarController
import maystruks08.gmail.com.romantic.ui.ToolbarDescriptor
import javax.inject.Inject

class ParticipantFragment : Fragment(), ParticipantContract.View {

    @Inject
    lateinit var presenter: ParticipantContract.Presenter

    @Inject
    lateinit var controller: ToolBarController

    private lateinit var adapter:  UserAdapter

    private var hikeId: String? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.participantComponent?.inject(this)
        presenter.bindView(this)
        hikeId = arguments?.getString(PARTICIPANT_HIKE_ID)

        return inflater.inflate(R.layout.fragment_participant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun configToolbar() {
        controller.configure(
            ToolbarDescriptor(
                true,
                "Participant",
                navigationIcon = R.drawable.ic_arrow_back_white_24dp,
                bottomBarVisibility = false
            ),
            activity as ConfigToolbar
        )
    }

    private fun initViews() {
        presenter.initUserList("sdg") //todo
        adapter = UserAdapter({ userItemClicked(it) },{ userInviteItemClicked(it) })
        participantRecyclerView.layoutManager = LinearLayoutManager(context)
        participantRecyclerView.adapter = adapter
    }

    private fun userItemClicked(user: User) {
        presenter.onUserClicked(user)
    }

    private fun userInviteItemClicked(user: User) {
        presenter.onInviteUserClicked(user)
    }

    override fun showParticipant(participants: List<User>) {
        adapter.userList = participants

    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {
    }

    companion object {


        private const val PARTICIPANT_HIKE_ID = "participantHikeID"

        fun getInstance(hikeId: String): ParticipantFragment =
            ParticipantFragment() .apply {
                arguments = Bundle().apply {
                    putString(PARTICIPANT_HIKE_ID, hikeId)
                }
            }
    }
}