package maystruks08.gmail.com.romantic.ui.main.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import maystruks08.gmail.com.domain.entity.TypeHike
import android.os.Parcelable
import maystruks08.gmail.com.romantic.ui.main.pager.HikeListContract
import maystruks08.gmail.com.romantic.ui.main.pager.HikePageFragment

class PagerAdapter(fragmentManager: FragmentManager, val presenter: HikeListContract.Presenter) : FragmentStatePagerAdapter(fragmentManager) {

    val fragmentList = listOf(
        HikePageFragment.getInstance(TypeHike.MOUNTAIN),
        HikePageFragment.getInstance(TypeHike.SKI),
        HikePageFragment.getInstance(TypeHike.WALKING),
        HikePageFragment.getInstance(TypeHike.WATER)
    )

    override fun getItem(position: Int): Fragment {
        presenter.initFragment(TypeHike.fromValue(position),position )
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Mountain"
            1 -> "Ski"
            2 -> "Walking"
            else -> "Water"
        }
    }

    override fun saveState(): Parcelable? {
        return null
    }
}