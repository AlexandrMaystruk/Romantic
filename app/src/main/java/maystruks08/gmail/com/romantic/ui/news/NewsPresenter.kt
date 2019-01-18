package maystruks08.gmail.com.romantic.ui.news

import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.News
import maystruks08.gmail.com.domain.interactor.news.NewsInteractor
import maystruks08.gmail.com.romantic.core.ABasePresenter
import maystruks08.gmail.com.romantic.ui.hikes.HikeListContract

import javax.inject.Inject


class NewsPresenter @Inject constructor(private val newsInteractor: NewsInteractor)
    : NewsContract.Presenter, ABasePresenter<NewsContract.View>() {


    override fun initNewsList(url: String): Single<News> {
        TODO("not implemented")
    }

    override fun getUpdateNewsList(url: String): Single<News> {
        TODO("not implemented")
    }


}
