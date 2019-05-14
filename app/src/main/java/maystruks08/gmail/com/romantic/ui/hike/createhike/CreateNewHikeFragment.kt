package maystruks08.gmail.com.romantic.ui.hike.createhike

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_create_new_hike.*
import maystruks08.gmail.com.domain.entity.Category
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.domain.entity.Route
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.ConfigToolbar
import maystruks08.gmail.com.romantic.ui.ToolBarController
import maystruks08.gmail.com.romantic.ui.ToolbarDescriptor
import maystruks08.gmail.com.romantic.ui.main.UploadListener
import java.util.*
import javax.inject.Inject

class CreateNewHikeFragment : Fragment(), CreateNewHikeContract.View {

    @Inject
    lateinit var presenter: CreateNewHikeContract.Presenter

    @Inject
    lateinit var controller: ToolBarController

    private lateinit var uploadListener: UploadListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.createHikeComponent?.inject(this)
        return inflater.inflate(R.layout.fragment_create_new_hike, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.bindView(this)
        initViews()
    }

    override fun configToolbar() {
        controller.configure(
            ToolbarDescriptor(
                true, "Create new hike",
                navigationIcon = R.drawable.ic_arrow_back_white_24dp,
                bottomBarVisibility = false
            ),
            activity as ConfigToolbar
        )
    }


    private fun initViews() {
        btnCreateHike.setOnClickListener {
            val newHike = Hike(
                Date().time,
                TypeHike.fromValue(spinnerType.selectedItemPosition),
                Date(cvDateOfHike.date),
                Date(cvDateOfHike.date),
                etBossHike.text.toString(),
                etHikeRegion.text.toString(),
                Category.fromValue(spinnerCategory.selectedItemPosition),
                mutableListOf()
            )

            presenter.createHike(newHike)
        }
    }

    override fun createHikeSuccess() {
        uploadListener.uploadHikes()
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
        fun getInstance(): CreateNewHikeFragment =
            CreateNewHikeFragment()
    }


}
