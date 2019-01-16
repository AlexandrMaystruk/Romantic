package maystruks08.gmail.com.romantic.ui.news

import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.News


interface NewsContract {

    interface View {

        fun updateUI(newsList: List<News>)

        fun showError(message: String)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {

        fun initNewsList(url: String) : Single<News>
        fun getUpdateNewsList(url: String) : Single<News>
    }

    interface Model {

        fun performParseHTML(url: String)
    }

    interface OnGetNewsListener {

        fun onInitSuccess(newsList: List<News>)

        fun onInitFailure(message: String)
    }
}
