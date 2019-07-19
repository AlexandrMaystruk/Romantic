package maystruks08.gmail.com.romantic.ui.chat

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import maystruks08.gmail.com.domain.entity.Message
import maystruks08.gmail.com.romantic.R
import kotlin.properties.Delegates

class ChatAdapter(private val clickListener: (Message) -> Unit) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    var messeges: List<Message> by Delegates.observable(mutableListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindHolder(messeges[position], clickListener)
    }

    override fun getItemCount(): Int = messeges.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindHolder(chat: Message, clickListener: (Message) -> Unit) {
            itemView.setOnClickListener { clickListener(chat) }
        }
    }
}