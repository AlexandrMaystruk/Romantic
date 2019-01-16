package maystruks08.gmail.com.romantic.ui.news

import io.reactivex.Single
import maystruks08.gmail.com.domain.entity.News
import maystruks08.gmail.com.domain.interactor.news.NewsInteractor

import javax.inject.Inject


class NewsPresenter @Inject constructor(
    private val iView: NewsContract.Presenter,
    private val newsInteractor: NewsInteractor
) : NewsContract.Presenter {


    override fun initNewsList(url: String): Single<News> {
        TODO("not implemented")
    }

    override fun getUpdateNewsList(url: String): Single<News> {
        TODO("not implemented")
    }


}
