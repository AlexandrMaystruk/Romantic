package maystruks08.gmail.com.romantic.ui.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R
import javax.inject.Inject

class ChatFragment : Fragment(), ChatContract.View {

    @Inject
    lateinit var presenter: ChatContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        App.chatComponent?.inject(this)
        presenter.bindView(this)

        return inflater.inflate(R.layout.fragment_chat, container, false)
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
