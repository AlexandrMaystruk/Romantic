package maystruks08.gmail.com.romantic.ui.selectedhike.participant

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_card_user.view.*
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.romantic.R
import kotlin.properties.Delegates


class ParticipantAdapter(private val clickListener: (Participant) -> Unit) : RecyclerView.Adapter<ParticipantAdapter.ViewHolder>() {

    var participantList: List<Participant> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindHolder(participantList[position], clickListener)
    }

    override fun getItemCount(): Int = participantList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindHolder(participant: Participant, clickListener: (Participant) -> Unit) {
//            itemView.iv_user_image.setImageBitmap()
            itemView.tv_user_name.text = participant.displayName
            itemView.setOnClickListener { clickListener(participant) }
        }
    }
}