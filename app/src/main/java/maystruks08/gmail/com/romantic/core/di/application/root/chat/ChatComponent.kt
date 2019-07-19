package maystruks08.gmail.com.romantic.core.di.application.root.chat

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.ui.chat.ChatFragment
import maystruks08.gmail.com.romantic.ui.chat.chatlist.ChatListFragment

@Subcomponent(modules = [ChatModule::class])
@ChatScope
interface ChatComponent {

    fun inject(chatFragment: ChatFragment)

    fun inject(chatListFragment: ChatListFragment)
}