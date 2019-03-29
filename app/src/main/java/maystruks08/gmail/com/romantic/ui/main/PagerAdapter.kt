package maystruks08.gmail.com.romantic.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.romantic.ui.hikes.HikeListFragment

class PagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private val fragmentList = listOf(
        HikeListFragment.getInstance(TypeHike.MOUNTAIN),
        HikeListFragment.getInstance(TypeHike.WALKING),
        HikeListFragment.getInstance(TypeHike.WATER),
        HikeListFragment.getInstance(TypeHike.SKI)
    )

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
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