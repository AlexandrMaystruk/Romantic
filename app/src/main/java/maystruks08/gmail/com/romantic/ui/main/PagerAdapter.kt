package maystruks08.gmail.com.romantic.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import maystruks08.gmail.com.romantic.ui.hikes.HikeListFragment

class PagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                HikeListFragment.getInstance(emptyList())
            }
            1 -> {
                HikeListFragment.getInstance(emptyList())
            }
            2 -> {
                HikeListFragment.getInstance(emptyList())
            }
            else ->  HikeListFragment.getInstance(emptyList())
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