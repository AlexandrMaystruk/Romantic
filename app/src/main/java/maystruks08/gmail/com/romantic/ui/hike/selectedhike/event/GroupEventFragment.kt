package maystruks08.gmail.com.romantic.ui.hike.selectedhike.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.*
import javax.inject.Inject

class GroupEventFragment : Fragment(), GroupeEventContract.View {

    @Inject
    lateinit var presenter: GroupeEventContract.Presenter

    @Inject
    lateinit var controller: ToolBarController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.eventComponent?.inject(this)
        presenter.bindView(this)

        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun configToolbar() {
        controller.configure(
            ToolbarDescriptor.Builder()
                .visibility(true)
                .title("Events")
                .build(),
            activity as ConfigToolbar
        )
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {

    }


    companion object {
        fun getInstance(): GroupEventFragment = GroupEventFragment()
    }


}
