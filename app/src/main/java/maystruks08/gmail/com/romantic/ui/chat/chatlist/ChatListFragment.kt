package maystruks08.gmail.com.romantic.ui.chat.chatlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_build_route.*
import maystruks08.gmail.com.domain.entity.Chat
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.ConfigToolbar
import maystruks08.gmail.com.romantic.ui.ToolBarController
import maystruks08.gmail.com.romantic.ui.ToolbarDescriptor
import javax.inject.Inject

class ChatListFragment : Fragment(), ChatListContract.View {

    @Inject
    lateinit var presenter: ChatListContract.Presenter

    @Inject
    lateinit var controller: ToolBarController

    private lateinit var listAdapter: ChatListAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        App.chatComponent?.inject(this)
        presenter.bindView(this)
        return inflater.inflate(R.layout.fragment_chat_list, container, false)
    }

    override fun configToolbar() {
        controller.configure(
            ToolbarDescriptor.Builder()
                .visibility(true)
                .title("Message")
                .build(),
            activity as ConfigToolbar
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    private fun initViews() {
        setAdapter()
    }

    private fun setAdapter() {
        listAdapter = ChatListAdapter { itemClicked(it) }
        recyclerViewGeoPoint.layoutManager = LinearLayoutManager(context)
        recyclerViewGeoPoint.adapter = listAdapter
    }


    private fun itemClicked(chat: Chat) {

    }

    override fun init(chatList: List<Chat>) {
        listAdapter.chats = chatList
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {

    }

    companion object {
        fun getInstance(): ChatListFragment =
            ChatListFragment()
    }
}
