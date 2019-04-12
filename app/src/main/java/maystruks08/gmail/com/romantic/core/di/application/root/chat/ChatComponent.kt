package maystruks08.gmail.com.romantic.core.di.application.root.chat

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.ui.chat.ChatFragment

@Subcomponent(modules = [ChatModule::class])
@ChatScope
interface ChatComponent {

    fun inject(chatFragment: ChatFragment)
}