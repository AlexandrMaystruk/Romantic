package maystruks08.gmail.com.romantic.ui.selectedhike.invitetogroup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_users.*
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.ConfigToolbar
import maystruks08.gmail.com.romantic.ui.ToolBarController
import maystruks08.gmail.com.romantic.ui.ToolbarDescriptor
import javax.inject.Inject

class InviteUserFragment : Fragment(),
    InviteUserContract.View {

    @Inject
    lateinit var presenter: InviteUserContract.Presenter

    @Inject
    lateinit var controller: ToolBarController

    private lateinit var adapter: UserAdapter

    private var hikeId: String? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.participantComponent?.inject(this)
        presenter.bindView(this)
        hikeId = arguments?.getString(USER_HIKE_ID)

        return inflater.inflate(R.layout.fragment_users, container, false)
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
        presenter.initUserList()
        adapter = UserAdapter({ userItemClicked(it) },
            { userInviteItemClicked(it) })
        usersRecyclerView.layoutManager = LinearLayoutManager(context)
        usersRecyclerView.adapter = adapter
    }

    private fun userItemClicked(user: User) {
        presenter.onUserClicked(user)
    }

    private fun userInviteItemClicked(user: User) {
        presenter.onInviteUserClicked(user)
    }

    override fun showUsers(users: List<User>) {
        adapter.userList = users
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {
    }

    companion object {


        private const val USER_HIKE_ID = "userHikeID"

        fun getInstance(hikeId: String): InviteUserFragment =
            InviteUserFragment().apply {
                arguments = Bundle().apply {
                    putString(USER_HIKE_ID, hikeId)
                }
            }
    }
}