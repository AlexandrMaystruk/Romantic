package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route.routelist


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_card_route.view.*
import maystruks08.gmail.com.domain.entity.Route
import maystruks08.gmail.com.romantic.R
import kotlin.properties.Delegates

class RouteAdapter(private val clickListener: (Route) -> Unit) : RecyclerView.Adapter<RouteAdapter.ViewHolder>() {

    var routeList: List<Route> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_route, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindHolder(routeList[position], clickListener)
    }

    override fun getItemCount(): Int = routeList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindHolder(route: Route, clickListener: (Route) -> Unit) {
            itemView.tvRouteName.text = route.type.name
            itemView.setOnClickListener { clickListener(route) }
        }
    }
}