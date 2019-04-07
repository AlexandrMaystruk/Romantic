package maystruks08.gmail.com.romantic.ui.selectedhike.participant

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_card_hike.view.*
import maystruks08.gmail.com.domain.entity.Hike
import kotlin.properties.Delegates


class UserAdapter(private val clickListener: (Hike) -> Unit) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var hikeList: List<Hike> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_hike, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindHolder(hikeList[position], clickListener)
    }

    override fun getItemCount(): Int = hikeList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindHolder(hike: Hike, clickListener: (Hike) -> Unit) {
            itemView.card_hike_chief.text = hike.hikeChief
            itemView.card_hike_date.text = hike.dateEnd.toString()
            itemView.card_hike_category.text = hike.category.toString()
            itemView.card_hike_region.text = hike.region
            itemView.setOnClickListener { clickListener(hike) }
        }
    }
}