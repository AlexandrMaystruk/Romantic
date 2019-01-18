package maystruks08.gmail.com.romantic.core.di.news

import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.data.repository.NewsRepositoryImpl
import maystruks08.gmail.com.data.room.AppDatabase
import maystruks08.gmail.com.data.room.dao.NewsDAO
import maystruks08.gmail.com.domain.interactor.news.NewsInteractor
import maystruks08.gmail.com.domain.interactor.news.NewsInteractorImpl
import maystruks08.gmail.com.domain.repository.NewsRepository
import maystruks08.gmail.com.romantic.ui.news.NewsContract
import maystruks08.gmail.com.romantic.ui.news.NewsPresenter

@Module
class NewsModule {

    @Provides
    @NewsScope
    fun newsDao(appDatabase: AppDatabase): NewsDAO = appDatabase.newsDAO()

    @Provides
    @NewsScope
    fun repository(newsRepository: NewsRepositoryImpl): NewsRepository = newsRepository

    @Provides
    @NewsScope
    fun interactor(newsInteractorImpl: NewsInteractorImpl): NewsInteractor = newsInteractorImpl

    @Provides
    @NewsScope
    fun presenter(newsPresenter: NewsPresenter): NewsContract.Presenter = newsPresenter


}