package maystruks08.gmail.com.romantic.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.romantic.ui.hikes.HikeListFragment

class PagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                HikeListFragment.getInstance(TypeHike.MOUNTAIN)
            }
            1 -> {
                HikeListFragment.getInstance(TypeHike.WALKING)
            }
            2 -> {
                HikeListFragment.getInstance(TypeHike.WATER)
            }
            else ->  HikeListFragment.getInstance(TypeHike.SKI)
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