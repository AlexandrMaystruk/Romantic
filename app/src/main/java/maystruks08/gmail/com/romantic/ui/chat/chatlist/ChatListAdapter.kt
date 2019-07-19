package maystruks08.gmail.com.romantic.ui.chat.chatlist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import maystruks08.gmail.com.domain.entity.Chat
import maystruks08.gmail.com.romantic.R
import kotlin.properties.Delegates

class ChatListAdapter(private val clickListener: (Chat) -> Unit) : RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {

    var chats: List<Chat> by Delegates.observable(mutableListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_message, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindHolder(chats[position], clickListener)
    }

    override fun getItemCount(): Int = chats.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindHolder(chat: Chat, clickListener: (Chat) -> Unit) {
            itemView.setOnClickListener { clickListener(chat) }
        }
    }
}