package maystruks08.gmail.com.domain.interactor.news

import maystruks08.gmail.com.domain.repository.NewsRepository
import javax.inject.Inject

class NewsInteractorImpl @Inject constructor(val newsRepository: NewsRepository) : NewsInteractor {
}