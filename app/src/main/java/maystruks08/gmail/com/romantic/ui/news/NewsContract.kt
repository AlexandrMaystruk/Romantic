package maystruks08.gmail.com.romantic.ui.news

import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.News
import maystruks08.gmail.com.romantic.core.BasePresenter
import maystruks08.gmail.com.romantic.core.BaseView


interface NewsContract {

    interface View : BaseView {

        fun updateUI(newsList: List<News>)
    }

    interface Presenter : BasePresenter<NewsContract.View> {

        fun initNewsList(url: String) : Single<News>
        fun getUpdateNewsList(url: String) : Single<News>
    }
}
