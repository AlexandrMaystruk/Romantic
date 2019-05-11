package maystruks08.gmail.com.romantic.ui.hike.selectedhike.invitetogroup

import android.graphics.drawable.ColorDrawable
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.item_card_user.view.*
import maystruks08.gmail.com.domain.entity.User
import maystruks08.gmail.com.domain.entity.UserPost
import maystruks08.gmail.com.romantic.R
import kotlin.properties.Delegates

class UserAdapter(private val clickListener: (User) -> Unit, private val inviteClickListener: (User, Int) -> Unit, private val removeClickListener: (User, Int) -> Unit) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var userList: List<User> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindHolder(userList[position], clickListener, inviteClickListener, removeClickListener)
    }

    override fun getItemCount(): Int = userList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindHolder(user: User, clickListener: (User) -> Unit, inviteClickListener: (User, Int) -> Unit,  removeClickListener: (User, Int) -> Unit) {
//            itemView.iv_user_image.setImageBitmap()  //todo load image with glide
            itemView.tv_user_name.text = user.displayName
            itemView.setOnClickListener { clickListener(user) }
            itemView.btnInviteToHike.setOnClickListener {
                if(isInviteClick(it as Button)){
                    inviteClickListener(user, itemView.spinnerPost.selectedItemPosition)
                    it.setBackgroundColor( ContextCompat.getColor(it.context,R.color.colorGreen ))
                    it.text = it.context.getString(R.string.user_invited)
                }else{
                    removeClickListener(user, itemView.spinnerPost.selectedItemPosition)
                    it.setBackgroundColor( ContextCompat.getColor(it.context,R.color.colorPrimary ))
                    it.text = it.context.getString(R.string.user_invite)
                }
            }
        }

        private fun isInviteClick(view: Button): Boolean{
           return (view.background as? ColorDrawable)?.color ==  ContextCompat.getColor(view.context,R.color.colorPrimary )
        }
    }
}