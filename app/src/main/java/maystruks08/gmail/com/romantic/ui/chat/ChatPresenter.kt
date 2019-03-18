package maystruks08.gmail.com.romantic.ui.chat

import maystruks08.gmail.com.domain.interactor.chat.ChatInteractor
import maystruks08.gmail.com.romantic.core.base.BasePresenter
import javax.inject.Inject

class ChatPresenter @Inject constructor(val chatInteractor: ChatInteractor) :
    ChatContract.Presenter, BasePresenter<ChatContract.View>() {

    override fun initUI() {

    }
}