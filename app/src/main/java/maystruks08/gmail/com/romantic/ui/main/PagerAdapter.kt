package maystruks08.gmail.com.romantic.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import maystruks08.gmail.com.domain.entity.Hike
import maystruks08.gmail.com.romantic.ui.hikes.HikeListFragment
import kotlin.properties.Delegates

class PagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    var hikeList: List<Hike> by Delegates.observable(emptyList()) {
            _,_,_ -> notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                HikeListFragment.getInstance(hikeList)
            }
            1 -> {
                HikeListFragment.getInstance(hikeList)
            }
            2 -> {
                HikeListFragment.getInstance(hikeList)
            }
            else ->  HikeListFragment.getInstance(hikeList)
        }

    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Mountain"
            1 -> "Walking"
            2 -> "Water"
            else -> {
                return "Ski"
            }
        }
    }
}