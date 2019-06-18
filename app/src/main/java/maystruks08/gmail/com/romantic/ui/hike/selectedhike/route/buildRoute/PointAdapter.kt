package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.buildRoute

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_build_route.view.*
import kotlinx.android.synthetic.main.item_route_point.view.*
import kotlinx.android.synthetic.main.item_route_point.view.ivPointType
import maystruks08.gmail.com.domain.entity.Point
import maystruks08.gmail.com.romantic.R
import kotlin.properties.Delegates
import java.util.*

class PointAdapter(private val clickListener: (Point) -> Unit, private val clickButtonListener: () -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchHelperAdapter {

    var changeItemCountListener: ChangeItemCountListener? = null

    fun addOnSizeChangeListener(changeItemCountListener: ChangeItemCountListener) {
        this.changeItemCountListener = changeItemCountListener
    }

    var pointList: MutableList<Point> by Delegates.observable(mutableListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        pointList[fromPosition].number = toPosition + 1
        pointList[toPosition].number = fromPosition + 1

        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(pointList, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(pointList, i, i - 1)
            }
        }
        notifyItemChanged(fromPosition)
        notifyItemChanged(toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemRemove(position: Int) {
        pointList.removeAt(position)
        notifyItemRemoved(position)

        if (pointList.size == 1) {
            pointList[0] = pointList.first().apply { number-- }
            notifyItemChanged(0)
        } else if (pointList.size > 1) {
            if (pointList.size == position) {
                val lastItem = pointList.last()
                pointList[pointList.lastIndex] = lastItem.apply { number-- }
                notifyItemChanged(position - 1)
            } else {
                for (i in position until pointList.size) {
                    pointList[i].number = i + 1
                    notifyItemChanged(i)
                }
            }
        }
        changeItemCountListener?.onItemListSizeChanged(pointList.size)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bindHolderGeoPoint(pointList[position], pointList.size, clickListener)
        } else if (holder is ViewHolderButton) {
            holder.bindHolderAddPoint(clickButtonListener)
            changeItemCountListener?.onItemListSizeChanged(pointList.size)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = when (viewType) {
            ITEM -> LayoutInflater.from(parent.context).inflate(R.layout.item_route_point, parent, false)
            else -> LayoutInflater.from(parent.context).inflate(R.layout.item_build_route, parent, false)
        }
        return if (viewType == ITEM) ViewHolder(view) else ViewHolderButton(view)
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == pointList.size) {
            BUTTON
        } else {
            ITEM
        }
    }

    override fun getItemCount(): Int = pointList.size + 1

    private inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindHolderGeoPoint(point: Point, listSize: Int, clickListener: (Point) -> Unit) {
            itemView.tvPointName.text = point.number.toString()
            itemView.ivPointType.setImageResource(getMarkerType(point.number, listSize))
            itemView.setOnClickListener { clickListener(point) }
        }

        private fun getMarkerType(pointNumber: Int, listSize: Int): Int {
            return when (pointNumber) {
                1 -> R.drawable.marker_circle_flag_start
                listSize -> R.drawable.marker_circle_finish
                else -> R.drawable.marker_blue
            }
        }
    }

    inner class ViewHolderButton(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            addOnSizeChangeListener(object : ChangeItemCountListener {
                override fun onItemListSizeChanged(size: Int) {
                    when {
                        size <= 1 -> {
                            itemView.btnBuildRoute.text =
                                itemView.context.getString(R.string.build_route_long_tap_to_build)
                            itemView.isClickable = false
                        }
                        size > 1 -> {
                            itemView.btnBuildRoute.text = itemView.context.getString(R.string.build_route)
                            itemView.isClickable = true
                        }
                    }
                }
            })
        }

        fun bindHolderAddPoint(clickListener: () -> Unit) {
            itemView.setOnClickListener { clickListener() }
        }
    }

    companion object {
        const val ITEM = 0
        const val BUTTON = 1
    }
}