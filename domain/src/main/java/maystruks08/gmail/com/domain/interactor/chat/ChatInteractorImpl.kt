package maystruks08.gmail.com.domain.interactor.chat

import maystruks08.gmail.com.domain.repository.ChatRepository
import javax.inject.Inject

class ChatInteractorImpl @Inject constructor(val repository: ChatRepository) : ChatInteractor {
}