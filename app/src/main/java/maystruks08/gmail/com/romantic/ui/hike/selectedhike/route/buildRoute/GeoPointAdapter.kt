package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.buildRoute

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_route_point.view.*
import maystruks08.gmail.com.domain.entity.Point
import maystruks08.gmail.com.romantic.R
import kotlin.properties.Delegates


class GeoPointAdapter(private val clickListener: (Point) -> Unit, private val clickButtonListener: () -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var hikeList: MutableList<Point> by Delegates.observable(mutableListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        hikeList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bindHolderGeoPoint(hikeList[position], clickListener)
        } else if (holder is ViewHolderButton) {
            holder.bindHolderAddPoint(clickButtonListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = when (viewType) {
            ITEM -> LayoutInflater.from(parent.context).inflate(R.layout.item_route_point, parent, false)
            else -> LayoutInflater.from(parent.context).inflate(R.layout.item_route_add_point, parent, false)
        }
        return if (viewType == ITEM) ViewHolder(view) else ViewHolderButton(view)
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == hikeList.size) {
            BUTTON
        } else {
            ITEM
        }
    }

    override fun getItemCount(): Int = hikeList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindHolderGeoPoint(point: Point, clickListener: (Point) -> Unit) {
            itemView.tvPointName.text = point.toString()
            itemView.setOnClickListener { clickListener(point) }
        }
    }

    inner class ViewHolderButton(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindHolderAddPoint(clickListener: () -> Unit) {
            itemView.setOnClickListener { clickListener() }
        }
    }


    companion object {
        const val ITEM = 0
        const val BUTTON = 1
    }
}