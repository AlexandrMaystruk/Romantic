package maystruks08.gmail.com.romantic.ui.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.ConfigToolbar
import maystruks08.gmail.com.romantic.ui.ToolBarController
import maystruks08.gmail.com.romantic.ui.ToolbarDescriptor
import javax.inject.Inject

class ChatFragment : Fragment(), ChatContract.View {

    @Inject
    lateinit var presenter: ChatContract.Presenter


    @Inject
    lateinit var controller: ToolBarController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        App.chatComponent?.inject(this)
        presenter.bindView(this)

        return inflater.inflate(R.layout.fragment_chat, container, false)
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


    override fun initUI(hikeList: List<Hike>) {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {

    }

    companion object {
        fun getInstance(): ChatFragment =
            ChatFragment()
    }


}
