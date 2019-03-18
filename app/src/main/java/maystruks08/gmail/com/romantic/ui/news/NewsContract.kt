package maystruks08.gmail.com.romantic.ui.news

import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.News
import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView


interface NewsContract {

    interface View : IView {

        fun updateUI(newsList: List<News>)
    }

    interface Presenter : IPresenter<View> {

        fun initNewsList(url: String) : Single<News>
        fun getUpdateNewsList(url: String) : Single<News>
    }
}
