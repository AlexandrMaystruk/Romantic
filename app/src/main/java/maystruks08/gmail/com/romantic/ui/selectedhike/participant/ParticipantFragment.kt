package maystruks08.gmail.com.romantic.ui.selectedhike.participant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.ui.ConfigToolbar
import maystruks08.gmail.com.romantic.ui.ToolBarController
import maystruks08.gmail.com.romantic.ui.ToolbarDescriptor
import javax.inject.Inject
import maystruks08.gmail.com.romantic.R

class ParticipantFragment : Fragment(), ParticipantContract.View {

    @Inject
    lateinit var presenter: ParticipantContract.Presenter

    @Inject
    lateinit var controller: ToolBarController


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.participantComponent?.inject(this)
        presenter.bindView(this)

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

    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {
    }

    companion object {

        fun getInstance(): ParticipantFragment =
            ParticipantFragment()
    }
}