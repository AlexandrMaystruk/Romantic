package maystruks08.gmail.com.romantic.ui.chat.chatlist

import maystruks08.gmail.com.domain.entity.Chat
import maystruks08.gmail.com.romantic.core.base.IPresenter
import maystruks08.gmail.com.romantic.core.base.IView

interface ChatListContract {

    interface View : IView {

        fun init(chatList: List<Chat>)
    }

    interface Presenter : IPresenter<View> {

        fun initUI()
    }
}
