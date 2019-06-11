package maystruks08.gmail.com.romantic.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import maystruks08.gmail.com.domain.entity.News
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.ConfigToolbar
import maystruks08.gmail.com.romantic.ui.ToolBarController
import maystruks08.gmail.com.romantic.ui.ToolbarDescriptor
import javax.inject.Inject

class NewsFragment : Fragment(), NewsContract.View {

    @Inject
    lateinit var presenter: NewsContract.Presenter


    @Inject
    lateinit var controller: ToolBarController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.newsComponent?.inject(this)
        presenter.bindView(this)

        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun updateUI(newsList: List<News>) {
    }

    override fun configToolbar() {
        controller.configure(ToolbarDescriptor.Builder()
            .visibility(true)
            .title("News")
            .build(), activity as ConfigToolbar)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {
    }

    companion object {

        fun getInstance(): NewsFragment = NewsFragment()
    }
}