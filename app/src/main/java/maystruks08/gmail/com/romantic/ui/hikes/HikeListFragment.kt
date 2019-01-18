package maystruks08.gmail.com.romantic.ui.hikes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R
import javax.inject.Inject

class HikeListFragment : Fragment(), HikeListContract.View {

    @Inject
    lateinit var presenter: HikeListContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        App.hikeComponent?.inject(this)
        presenter.bindView(this)

        return inflater.inflate(R.layout.fragment_hike_list, container)
    }


    override fun initUI(hikeList: Single<List<Hike>>) {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {

    }


    companion object {
        fun getInstance(): HikeListFragment = HikeListFragment()
    }
}

