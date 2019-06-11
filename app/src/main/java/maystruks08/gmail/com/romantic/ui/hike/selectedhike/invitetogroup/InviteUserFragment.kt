package maystruks08.gmail.com.romantic.ui.hike.selectedhike.invitetogroup

import android.content.Context
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
import maystruks08.gmail.com.romantic.ui.main.UploadListener
import javax.inject.Inject

class InviteUserFragment : Fragment(),
    InviteUserContract.View {

    @Inject
    lateinit var presenter: InviteUserContract.Presenter

    @Inject
    lateinit var controller: ToolBarController

    private lateinit var adapter: UserAdapter

    private var hikeId: Long? = null

    private lateinit var uploadListener: UploadListener


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.participantComponent?.inject(this)
        presenter.bindView(this)
        hikeId = arguments?.getLong(USER_HIKE_ID)

        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun configToolbar() {
        controller.configure(
            ToolbarDescriptor.Builder()
                .visibility(true)
                .title("Invite to hike")
                .navigationIcon(R.drawable.ic_arrow_back_white_24dp)
                .bottomBarVisibility(false)
                .menu(R.menu.menu_invite_user)
                .build(),
            activity as ConfigToolbar
        )
        controller.addMenuClickListener(activity as ConfigToolbar, ::onMenuClick)

    }

    private fun onMenuClick(menuId: Int) {
        if (menuId == R.id.action_save_end_exit) {
            presenter.onSaveClicked(hikeId!!)
        }
    }

    private fun initViews() {
        presenter.initUserList()
        adapter = UserAdapter({
            userItemClicked(
                hikeId ?: 0L, it
            )
        },
            { user, postPosition -> userInviteItemClicked(user, postPosition) },
            { user, postPosition -> userRemoveItemClicked(user, postPosition) })
        usersRecyclerView.layoutManager = LinearLayoutManager(context)
        usersRecyclerView.adapter = adapter
    }

    private fun userItemClicked(hikeId: Long, user: User) {
        presenter.onUserClicked(hikeId, user)
    }

    private fun userInviteItemClicked(user: User, postPosition: Int) {
        hikeId?.let {
            presenter.onInviteUserClicked(user, postPosition, it)
        }
    }

    private fun userRemoveItemClicked(user: User, postPosition: Int) {
        hikeId?.let {
            presenter.onRemoveUserClicked(user, postPosition, it)
        }
    }

    override fun showUsers(users: List<User>) {
        adapter.userList = users
    }

    override fun inviteUserSuccess() {
        uploadListener.uploadParticipants()
    }


    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is UploadListener) {
            uploadListener = activity as UploadListener
        }
    }

    companion object {


        private const val USER_HIKE_ID = "userHikeID"

        fun getInstance(hikeId: Long): InviteUserFragment =
            InviteUserFragment().apply {
                arguments = Bundle().apply {
                    putLong(USER_HIKE_ID, hikeId)
                }
            }
    }
}