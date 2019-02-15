package maystruks08.gmail.com.romantic.ui.hike

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.ConfigToolbar
import maystruks08.gmail.com.romantic.ui.ToolBarController
import maystruks08.gmail.com.romantic.ui.ToolbarDescriptor
import javax.inject.Inject

class HikeFragment : Fragment(), HikeContract.View {

    @Inject
    lateinit var presenter: HikeContract.Presenter

    @Inject
    lateinit var controller: ToolBarController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        App.hikeComponent?.inject(this)
        presenter.bindView(this)

        return inflater.inflate(R.layout.fragment_selected_hike, container, false)
    }

    override fun configToolbar() {
        controller.configure(ToolbarDescriptor(true, "Hike",
            navigationIcon = R.drawable.ic_arrow_back_white_24dp), activity as ConfigToolbar)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {

    }


    companion object {
        const val TAG = "HikeFragment"

//        fun getInstance(hike: Hike): HikeFragment =
//            HikeFragment().apply { arguments?.putParcelable(TAG, hike) }

        fun getInstance(): HikeFragment =
            HikeFragment()
    }
}
