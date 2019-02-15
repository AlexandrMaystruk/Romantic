package maystruks08.gmail.com.romantic.ui.financerhelper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.ConfigToolbar
import maystruks08.gmail.com.romantic.ui.ToolBarController
import maystruks08.gmail.com.romantic.ui.ToolbarDescriptor
import javax.inject.Inject

class FinancerHelperFragment : Fragment(), FinancerHelperContract.View {

    @Inject
    lateinit var presenter: FinancerHelperContract.Presenter

    @Inject
    lateinit var controller: ToolBarController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_financer_helper, container)
    }

    override fun configToolbar() {
        controller.configure(ToolbarDescriptor(false), activity as ConfigToolbar)
    }

    override fun initUI() {
        TODO("not implemented")
    }

    override fun showLoading() {
        TODO("not implemented")
    }

    override fun hideLoading() {
        TODO("not implemented")
    }

    override fun showError(t: Throwable) {
        TODO("not implemented")
    }

    companion object {

        fun getInstance(): FinancerHelperFragment = FinancerHelperFragment()
    }
}

