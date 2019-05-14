package maystruks08.gmail.com.romantic.ui.hike.selectedhike.participant

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_card_participant.view.*
import maystruks08.gmail.com.domain.entity.Participant
import maystruks08.gmail.com.romantic.R
import kotlin.properties.Delegates


class ParticipantAdapter(private val clickListener: (Participant) -> Unit) : RecyclerView.Adapter<ParticipantAdapter.ViewHolder>() {

    var participantList: MutableList<Participant> by Delegates.observable(mutableListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        participantList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_participant, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindHolder(participantList[position], clickListener)
    }

    override fun getItemCount(): Int = participantList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindHolder(participant: Participant, clickListener: (Participant) -> Unit) {
//            itemView.iv_user_image.setImageBitmap()
            itemView.tvParticipantName.text = participant.user.displayName
            itemView.setOnClickListener { clickListener(participant) }
        }
    }
}