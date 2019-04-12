package maystruks08.gmail.com.romantic.core.di.application.root.chat

import dagger.Module
import dagger.Provides
import maystruks08.gmail.com.data.repository.ChatRepositoryImpl
import maystruks08.gmail.com.domain.interactor.chat.ChatInteractor
import maystruks08.gmail.com.domain.interactor.chat.ChatInteractorImpl
import maystruks08.gmail.com.domain.repository.ChatRepository
import maystruks08.gmail.com.romantic.ui.chat.ChatContract
import maystruks08.gmail.com.romantic.ui.chat.ChatPresenter

@Module
class ChatModule {

    @Provides
    @ChatScope
    fun presenter(chatPresenter: ChatPresenter): ChatContract.Presenter = chatPresenter

    @Provides
    @ChatScope
    fun repository(chatRepository: ChatRepositoryImpl): ChatRepository = chatRepository

    @Provides
    @ChatScope
    fun interactor(chatInteractorImpl: ChatInteractorImpl): ChatInteractor = chatInteractorImpl
}
