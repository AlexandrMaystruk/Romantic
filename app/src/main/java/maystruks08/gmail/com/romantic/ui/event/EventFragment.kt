package maystruks08.gmail.com.romantic.ui.event

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

class EventFragment : Fragment(), EventContract.View {

    @Inject
    lateinit var presenter: EventContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        App.eventComponent?.inject(this)
        presenter.bindView(this)

        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {

    }


    companion object {
        fun getInstance(): EventFragment = EventFragment()
    }


}
