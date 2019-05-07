package maystruks08.gmail.com.romantic.ui.selectedhike.invitetogroup

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_card_user.view.*
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.entity.UserPost
import maystruks08.gmail.com.romantic.R
import kotlin.properties.Delegates

class UserAdapter(private val clickListener: (User) -> Unit, private val inviteClickListener: (User, Int) -> Unit) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var userList: List<User> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindHolder(userList[position], clickListener, inviteClickListener)
    }

    override fun getItemCount(): Int = userList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindHolder(user: User, clickListener: (User) -> Unit, inviteClickListener: (User, Int) -> Unit) {
//            itemView.iv_user_image.setImageBitmap()
            itemView.tv_user_name.text = user.displayName
            itemView.setOnClickListener { clickListener(user) }
            itemView.btnInviteToHike.setOnClickListener { inviteClickListener(user, itemView.spinnerPost.selectedItemPosition) }
        }
    }
}